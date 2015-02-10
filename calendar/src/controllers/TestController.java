package controllers;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import service.ServiceDao;
import dao.Events;
import dao.Lecture;
import dao.StreamDao;
import dao.Stream;
import dao.User;
import dao.UserDao;


@Controller
public class TestController {
	
private UserDao userDao;
private ServiceDao service;
private Validation validation;
private StreamDao streamDao;
	

@Autowired
public TestController(ServiceDao service, Validation validation , StreamDao streamDao) {
	this.service = service;
	this.validation = validation;
	this.streamDao = streamDao;
}

	
	@RequestMapping("/testDao")
	public String testing(Model model) {
		/*Principal p = null;
		String username = p.getName();
		//get user with id so I can use it to test parts of code
		User user = service.getUser(username);
		
		/ user with id to be active so he can log in to site
		user.setEnabled(true);
		
		model.addAttribute("user", new User());
		
		model.addAttribute("stream", new Stream());
		
		
		List<Stream> streams = service.getStreams();
		
		
		
		model.addAttribute("streams", streams);
		
		List<Lecture> lectures = service.getLectures();
		
		model.addAttribute("lectures", lectures);*/
		
		return "testDao";
	}
	
	/*@RequestMapping("/edittimetable")
	public String editTimes(Model model) {
		
	
		
		List<Lecture> lectures = service.getLectures();
		
		model.addAttribute("lectures", lectures);
		
		return "edittimetable";
	}*/
	
	
	@RequestMapping("/events")
	public String getEvents(Model model) {
		
	

		
		return "events";
	}
	
	
	@RequestMapping(value="/getdata", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Object getMessages(Principal principal) {
		
		List<Stream> streams2 = null;
		User user = null;
		List<Events> events = null;		
		if(principal == null) {
			streams2 =null;
		}
		else {
			
			String username = principal.getName();
			user = service.getUser(username);
			streams2 = service.getStreams();
			events = service.getEvents();
			
		}
		
		
		Object data2 =events;
		
		
		
		
		
		return data2;
	}

}