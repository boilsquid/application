package controllers;

import java.math.BigInteger;
import java.security.Principal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import service.ServiceDao;
import dao.Authority;
import dao.Events;
import dao.Group;
import dao.GroupMember;
import dao.Lecture;
import dao.StreamDao;
import dao.Stream;
import dao.User;
import dao.UserDao;

@Controller
@SessionAttributes("streams")
/* keep streams in the forms for session */
public class RegisterController {

	private UserDao userDao;
	private ServiceDao service;
	private Validation validation;
	private SecureRandom random = new SecureRandom();
	private String token = secureToken();

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public RegisterController(ServiceDao service, Validation validation) {
		this.service = service;
		this.validation = validation;
	}
	
	private String secureToken(){
		return new BigInteger(130, random).toString(32);
	}
	
	@RequestMapping("/users")
	public String showUsers(Model model) {

		List<User> users = service.getUsers();

		model.addAttribute("users", users);

		return "users";
	}

	@RequestMapping("/register")
	public String registerUser(Model model) {

		model.addAttribute("user", new User());

		model.addAttribute("stream", new Stream());

		List<Stream> streams = service.getStreams();

		model.addAttribute("streams", streams);

		return "register";
	}

	@RequestMapping(value = "/doregister", method = RequestMethod.POST)
	public String doregister(Model model, @Valid User user, BindingResult result) {

		/* validating email to be restricted in the domain of the college */
		if (validation.isCollegeEmail(user.getEmail(), user.getRoleId()) == false) {
			result.rejectValue("email", "error.user",
					validation.getErrorMessageCollegeEmail());
		}

		if(result.hasErrors()){
			/*
			 * return user to register page to fix errors
			 */
			return "register";
		}else {
			try {
				/* encode users password */
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				/* enable user for testing purposes */
				//user.setEnabled(true);
				
				user.setActivationToken(token);
				String url = "http://localhost:8080/calendar/useractivation?username="+ user.getUserName() +"&&token=" + token;
				
				service.createUser(user);
				
				Authority authority = new Authority();
				authority.setAuthority(user.getRoleId());
				authority.setUserName(user.getUserName());
				service.createAuthority(authority);
				
				service.sendRegistrationMail(user.getEmail(), "Administration", "Activation", "Please follow this link to activate your email" + url);
				
				return "redirect:login";
			} catch (DuplicateKeyException e) {
				result.rejectValue("email", "DuplicateKey.user.email",
						"This email or username already exists!");
				return "register";
			}
		}
	}
	
	@RequestMapping(value = "/useractivation", method = RequestMethod.GET)
	public String activateUser(@RequestParam(value = "username", required = true) String userName, @RequestParam(value = "token",required = true) String token){
		// Find the user from the url param username
		User user = service.findUser("username", userName);
		// If the user exsits
		if(user != null){
			// Set a string usertoken to the users database password reset token
			String usertoken = user.getActivationToken();
			// If they are equal return the password reset form
			if(usertoken.equals(token)){
				user.setEnabled(true);
				service.updateUser(user);
				return "redirect:login?success=Your account has been activated";
			// Else return the reset page;
			}else{
				return "redirect:login?danger=Invalid activation token.";
			}
		}else{
			return "login";
		}
	}
	
	
	@RequestMapping("/editlectures")
	public String editLectures(Model model, Principal principal) {
		//get logged in users username
		String userName = principal.getName();
		
		/* create a user that is the currently logged in one*/
		User user = new User();
		user = service.getUser(userName);
		
	

		List<Lecture> lectures = service.getLectures();

		model.addAttribute("lectures", lectures);
		model.addAttribute("user", user);

		return "editlectures";
	}
	
	@RequestMapping(value = "/changelectures", method = RequestMethod.POST)
	public String editLectures(@RequestParam("lectureId") int[] lectureId, Principal p) {
		
		String username = p.getName();
		User user = service.getUser(username);
		
		/*delete the user from userEvents where type is lecture*/
		service.deleteFromWhere("userEvents", username, "lecture");
		/*delete all entries in group members created by admin*/
		service.deleteFromGroupMembers("groupMembers", username, "administration");
		
		/*need to delete them from groups
		 * get group id
		 * delete group member from members with group id
		 */
		
		int prevGroupId=0;//record the previous groupId
		for(int item: lectureId){
			
		
			/* get the lecture from lectureid sec#lected in form*/
			Lecture lecture = service.getLecture(item);
			System.out.println("causing problems "+lecture.getModuleId());//print for testing
			
			/* get the group id corresponding to lecture(module)
			 * insert user into group members
			 */
			Group group=service.getGroupByName(lecture.getModuleId());
			GroupMember member = new GroupMember();
			int groupId = group.getGroupId();
			member.setGroupId(groupId);
			member.setUserName(username);
			if(groupId!=prevGroupId){//only insert user into group members if user is not in group
				service.createGroupMember(member);
			}
			
			/*create an event to add to the users events calender
			 * and add the events data
			 */
			
			Events event = new Events();
			event.setUserName(username);
			event.setStart(lecture.getStart());
			event.setEnd(lecture.getEnd());
			event.setRecurring(lecture.getRecurring());
			event.setEventType("lecture");// hard coded for now
			event.setTypeId(0);
			event.setTitle(lecture.getModuleId());
			service.createEvent(event);
			
			prevGroupId = groupId;
			
		}
		
		
		
		return "events";
		
		
		
	}

}