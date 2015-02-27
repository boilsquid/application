package controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String resetPassword(@RequestParam(value = "email", required = true) String userEmail, RedirectAttributes redirectAttributes){
		User user = service.findUser("email", userEmail);
		if(user != null){
			user.setPasswordResetToken(token);
			user.setLastName("newlastname2");
			String url = "http://localhost:8080/calendar/passwordreset?username="+ user.getUserName() +"&&token=" + token;
			service.updateUser(user);
			service.passwordResetMail(userEmail, "springuccproject@gmail.com", "Password Reset", "Follow this link to reset password "+url);
	        System.out.println(redirectAttributes);
	        return "redirect:login?success=A link has been sent to your email";
		}else{
			return "redirect:login?danger=Unable to find email in the system";
		}
	}
	
	@RequestMapping(value = "/passwordreset", method = RequestMethod.GET)
	public String passwordReset(@RequestParam(value = "username", required = true) String userName, @RequestParam(value = "token",required = true) String token){
		// Find the user from the url param username
		User user = service.findUser("username", userName);
		// If the user exsits
		if(user != null){
			// Set a string usertoken to the users database password reset token
			String usertoken = user.getPasswordResetToken();
			// If they are equal return the password reset form
			if(usertoken.equals(token)){
				return "passwordreset";
			// Else return the reset page;
			}else{
				return "redirect:reset?danger=Invalid password reset token.";
			}
		}else{
			return "reset";
		}
	}
	
	@RequestMapping(value = "/passwordreset", method = RequestMethod.POST)
	public String userResetPassword(@RequestParam(value = "password", required = true) String password, @RequestParam(value = "username", required = true) String userName){
		// Find the user by the username param
		User user = service.findUser("username", userName);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(user != null){
			String encodedPassword = encoder.encode(password);
			user.setPassword(encodedPassword);
			service.updateUser(user);
			return "login";
		}
		else{
			return "passwordreset";
		}
	}
	
	private String secureToken(){
		return new BigInteger(130, random).toString(32);
	}
	
}
