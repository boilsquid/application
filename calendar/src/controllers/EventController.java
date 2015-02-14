package controllers;


import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
public class EventController {
	
private UserDao userDao;
private ServiceDao service;
private Validation validation;
private StreamDao streamDao;

private List<User> group;
private String eventType;
	

@Autowired
public EventController(ServiceDao service, Validation validation , StreamDao streamDao) {
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
	
	/*@RequestMapping("/createevent")
	public String createEvents(Model model) {
		
		lectureGroup =null;

		
		return "events";
	}*/
	
	@RequestMapping("/setgroupview")
	public String createEvents(Model model) {
		
		group = service.getUsers();

		
		return "events";
	}
	
	
	@RequestMapping(value="/getdata", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public  Object getMessages(Principal principal) {
		
		
		/*get group name id
		 * get calender type tp show like is it just personal calender, meeting calender or tutorial
		 * set the events to required type
		 */
		
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
			events = service.getUserEvents(username);
			
		}
		
		
		/*if group is not equal null show the groups view data*/
		if(group!=null){
			/*getting all user events for testing*/
			events = service.getEvents();
		}
		
		
		
		ArrayList<Events> data =  new ArrayList<>();
		
		for(Events item: events){
			
		
		
			for(int i=0;i<item.getRecurring();i++){
				int id= item.getId();
				
				Calendar newStart = Calendar.getInstance();
				newStart.setTime(item.getStart());
				Calendar newEnd = Calendar.getInstance();
				newEnd.setTime(item.getEnd());
				newStart.add(Calendar.DAY_OF_WEEK, 7*i);
				newEnd.add(Calendar.DAY_OF_WEEK, 7*i);
				
				Events event = new Events();
				
				event.setId(id*(i+1));
				event.setUserName(item.getUserName());
				event.setStart(new Timestamp(newStart.getTimeInMillis()));
				event.setEnd(new Timestamp(newEnd.getTimeInMillis()));
				event.setTitle(item.getTitle());
				
				
				data.add(event);
				
				
			}
			
			
			
		}
		System.out.println(data);
		return data;/*this is data for a personal events feed*/
		
		/*come up with a feed for creating meetings
		 * group feed to check availablility
		 * return data as group feed
		 */
		
	}
	
	

}