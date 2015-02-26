package controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import service.ServiceDao;
import dao.Sql;
import dao.StreamDao;
import dao.User;
import dao.UserDao;

import java.math.BigInteger;
import java.security.SecureRandom;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	private ServiceDao service;
	private SecureRandom random = new SecureRandom();
	private String token = secureToken();
	
	@Autowired
	public LoginController(ServiceDao service) {
		this.service = service;
	}
	
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/reset")
	public String showReset(){
		return "reset";
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String resetPassword(@RequestParam(value = "email", required = true) String userEmail){
		//System.out.println(userEmail);
		User user = service.findUser("email", userEmail);
		
		if(user != null){
			System.out.println(userEmail);
			System.out.println(" token is "+token);
			user.setPasswordResetToken(token);
			user.setLastName("newlastname2");
			String url = "http://localhost:8080/calendar/passwordreset?username="+ user.getUserName() +"&&token=" + token;
			service.updateUser(user);
			System.out.println("the user after update: "+user);
			service.passwordResetMail(userEmail, "springuccproject@gmail.com", "Password Reset", "Follow this link to reset password <a href="+url+">Click Here</a>");
		}
		
		
		return "login";
	}
	
	@RequestMapping(value = "/passwordreset", method = RequestMethod.GET)
	public String passwordReset(@RequestParam(value = "username", required = true) String userName, @RequestParam(value = "token",required = true) String token){
		User user = service.findUser("username", userName);
		System.out.println(token);
		System.out.println(user);
		
		
		return "passwordreset";
	}
	
	private String secureToken(){
		return new BigInteger(130, random).toString(32);
	}
	
}
