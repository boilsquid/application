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

import service.ServiceDao;
import dao.Authority;
import dao.StreamDao;
import dao.Stream;
import dao.User;
import dao.UserDao;

@Controller
@SessionAttributes("streams")/*keep streams in the forms for session*/
public class RegisterController {
	
private UserDao userDao;
private ServiceDao service;
private Validation validation;
	

@Autowired
public RegisterController(ServiceDao service, Validation validation) {
	this.service = service;
	this.validation = validation;
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
	
	@RequestMapping(value="/doregister", method=RequestMethod.POST)
	public String doregister(Model model, @Valid User user, BindingResult result) {
		if(validation.isCollegeEmail(user.getEmail(),user.getRoleId())==false){
			 result.rejectValue("email", "error.user", validation.getErrorMessageCollegeEmail());
		}
		if(result.hasErrors()) {
			/*
			 * return user to register page to fix errors
			 */
			return "register";
		}else{
			
			/*If the users registers with no errors he is created and and entry to authorities table is made
			 * set his role and username and that table
			 */
			Authority authority = new Authority();
			authority.setAuthority(user.getRoleId());
			authority.setUserName(user.getUserName());			
			service.createAuthority(authority);
			service.createUser(user);
			return "doregister";
		}
		
		
		
	}
	

}