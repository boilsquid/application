package controllers;


import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import service.ServiceDao;
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
		
		//get user with id so I can use it to test parts of code*/
		User user = service.getUser(2);
		
		/*  user with id to be active so he can log in to site*/
		user.setEnabled(true);
		
		model.addAttribute("user", new User());
		
		model.addAttribute("stream", new Stream());
		
		
		List<Stream> streams = service.getStreams();
		
		
		
		model.addAttribute("streams", streams);
		
		List<Lecture> lectures = service.getLectures();
		
		model.addAttribute("lectures", lectures);
		
		return "testDao";
	}
	
	@RequestMapping("/edittimetable")
	public String editTimes(Model model) {
		
		
		List<Lecture> lectures = service.getLectures();
		
		model.addAttribute("lectures", lectures);
		
		return "edittimetable";
	}
	

}