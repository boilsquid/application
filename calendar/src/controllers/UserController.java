package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import dao.StreamDao;
import dao.Stream;
import dao.User;
import dao.UserDao;

@Controller
@SessionAttributes("streams")/*keep streams in the forms for session*/
public class UserController {
	
private UserDao userDao;
private StreamDao streamDao;
private Validation validation;
	

@Autowired
public UserController(UserDao userDao, StreamDao streamDao,Validation validation) {
	this.userDao = userDao;
	this.streamDao = streamDao;
	this.validation = validation;
}




	@RequestMapping("/users")
	public String showUsers(Model model) {
		
		List<User> users = userDao.getUsers();
		
		model.addAttribute("users", users);
		
		return "users";
	}
	
	
	@RequestMapping("/register")
	public String registerUser(Model model) {
		
		model.addAttribute("user", new User());
		
		
		model.addAttribute("stream", new Stream());
		
		List<Stream> streams = streamDao.getStreams();
		
		model.addAttribute("streams", streams);
		
		return "register";
	}
	
	@RequestMapping(value="/doregister", method=RequestMethod.POST)
	public String doregister(Model model, @Valid User user, BindingResult result) {
		if(validation.isCollegeEmail(user.getEmail(),user.getRoleId())==false){
			 result.rejectValue("email", "error.user", validation.getErrorMessageCollegeEmail());
		}
		if(result.hasErrors()) {
			
			return "register";
		}else{
			userDao.create(user);
			return "users";
		}
		
		
		
	}
	

}
