package admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
	
	@RequestMapping("/admin/dashboard")
	public String index(Model model){
		
		
		
		// Return the admin dashboard page
		return "admin/dashboard";
	}
}
