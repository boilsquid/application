package admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.User;
import service.ServiceDao;

@Controller
public class UsersController {
	
	private ServiceDao service;

	@Autowired
	public UsersController(ServiceDao service) {
		this.service = service;
	}

	@RequestMapping("/admin/users")
	public String index(Model model){
		// Get a list of all the current users in the database to
		// display on the index page.
		List<User> users = service.getUsers();
		
		model.addAttribute("users", users);
		
		// Print for testing
		for(User user: users){
			System.out.println(user.getEmail());
		}
		
		// Return the admin users index page
		return "admin/users/index";
	}
	
}
