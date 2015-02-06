package controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
@SessionAttributes("streams")/*keep streams in the forms for session*/
public class TestController {
	
private UserDao userDao;
private ServiceDao service;
private Validation validation;
	

@Autowired
public TestController(ServiceDao service, Validation validation) {
	this.service = service;
	this.validation = validation;
}

	
	@RequestMapping("/testDao")
	public String testing(Model model) {
		
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