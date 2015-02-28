package controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import dao.Notification;
import service.ServiceDao;

@Controller
@SessionAttributes({"notes", "noteCount"}) 
public class NotificationController {
		
	private ServiceDao service;
	List<Notification> notes = new ArrayList<>();
	
	int noteCount=0;
	
	@Autowired
	public NotificationController(ServiceDao service) {
		
		this.service = service;
	
	}

	@RequestMapping("/notes")
	public String displayNotes(Model model, Principal p) {
		
		String username = p.getName();
		
		/*get count of notes*/
		noteCount= service.getCountNotes(username);
		model.addAttribute("noteCount", noteCount);
		System.out.println("note count: "+noteCount);
		
		/*get list of notifactions in users inbox*/
		notes= service.getPersonalNotes(username);
		
		model.addAttribute("notes", notes);
		
		return "notes";
	}
	
	@RequestMapping("/deletenotes")
	public String deleteNotes(Model model,@RequestParam("notesId") int[] notesId) {
		
		/*delete notifications*/
		for(int item: notesId){
			System.out.println(item);
			service.deleteNote(item);
		}
		
		return "forward:/notes";
	}
}
