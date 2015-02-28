package controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showHome(Principal p) {
		/*if the user is logged in the homepage is the calendar
		 * otherwise send user to registration page
		 */
		if(p!=null){
			
			return "forward:/events";
			
		}else{
			return "forward:/register";
		}
		
	
	}
	
	@RequestMapping("/admin")
	public String showadmin() {
		return "admin";
	}
	
	@RequestMapping("/index2")
	public String showpage() {
		return "index2";
	}
}
