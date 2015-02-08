package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@SessionAttributes("streams")
/* keep streams in the forms for session */
public class RegisterController {

	private UserDao userDao;
	private ServiceDao service;
	private Validation validation;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@RequestMapping(value = "/doregister", method = RequestMethod.POST)
	public String doregister(Model model, @Valid User user, BindingResult result) {
		
		
		
		/* validating email to be restricted in the domain of the college */
		if (validation.isCollegeEmail(user.getEmail(), user.getRoleId()) == false) {
			result.rejectValue("email", "error.user",
					validation.getErrorMessageCollegeEmail());
		}

		if (result.hasErrors()) {
			/*
			 * return user to register page to fix errors
			 */
			return "register";
		} else {

			/*
			 * try to give an authority to user but if the username already
			 * exists tell user to pick another
			 */
			try {

				Authority authority = new Authority();
				authority.setAuthority(user.getRoleId());
				authority.setUserName(user.getUserName());
				service.createAuthority(authority);
				
				
			} catch (DuplicateKeyException e) {
				result.rejectValue("userName", "DuplicateKey.user.userName",
						"This userName already exists pick another!");
				return "register";
			}

			try {
				
				/*encode users password*/
				 user.setPassword(passwordEncoder.encode(user.getPassword()));
				
				 /*enable user for testing purposes*/
				 user.setEnabled(true);
				 
				service.createUser(user);
				return "doregister";
			} catch (DuplicateKeyException e) {
				
				result.rejectValue("email", "DuplicateKey.user.email",
						"This email already exists!");
				return "register";
			}

		}

	}

}