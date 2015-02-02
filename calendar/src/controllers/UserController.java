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

import dao.StreamDao;
import dao.Stream;
import dao.User;
import dao.UserDao;

@Controller
public class UserController {
	
private UserDao userDao;
private StreamDao streamDao;	
	

@Autowired
public void setUserDao(UserDao userDao, StreamDao streamDao) {
	this.userDao = userDao;
	this.streamDao = streamDao;
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
		
		if(result.hasErrors()) {
			return "register";
		}else{
			userDao.create(user);
			return "users";
		}
		
		
		
	}
	

}
