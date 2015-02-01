package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.UserService;
import dao.User;

@Controller
public class UserController {
	
private UserService userService;
	
	

@Autowired
public void setUserService(UserService userService) {
	this.userService = userService;
}




	@RequestMapping("/users")
	public String showUsers(Model model) {
		
		List<User> users = userService.getCurrent();
		
		model.addAttribute("users", users);
		
		return "users";
	}
	

	@RequestMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/doregister", method=RequestMethod.POST)
	public String doregister(Model model, @Valid User user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "register";
		}
		
		return "doregister";
	}
	

}
