package controllers;

import java.security.Principal;
//import java.sql.Date;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import service.ServiceDao;
import dao.Events;
import dao.GroupEvent;
import dao.Lecture;
import dao.Notification;
import dao.Sql;
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
	private Sql sql;

	private List<User> groupMembers = new ArrayList<>();
	private String eventType;
	private int groupId = 0;
	private int type = 0;

	/* variables to show different feeds of events */

	private boolean modIsSet = true;
	private boolean meetIsSet = true;
	private boolean perIsSet = true;

	/* variable to control type of events to be booked */
	private boolean personalAllowed = true;
	private boolean tutorialAllowed = true;
	private boolean meetingAllowed = true;

	/* return this value to form in events jsp */
	private int maxWeeks = 12;
	
	/*variable to display notifications in users inbox*/
	private int displayCountNotes=0;

	@Autowired
	public EventController(ServiceDao service, Validation validation,
			StreamDao streamDao, Sql sql) {
		this.sql = sql;
		this.service = service;
		this.validation = validation;
		this.streamDao = streamDao;
	}

	@RequestMapping("/testDao")
	public String testing(Model model) {

		return "testDao";
	}

	@RequestMapping("/events")
	public String getEvents(Model model, Principal p,HttpServletRequest request) {
		String username = p .getName();
		User user =service.getUser(username);
		String role = user.getRoleId();
		
		/*get the notifcation coutn*/
		displayCountNotes = service.getCountNotes(username);
		
		/* get the noteCount from the note controller which is stored in session
		 * check if the variable have been activated in session
		 * this secound get notes will update notes when the user uses the notification service
		 */
		if(request.getSession().getAttribute("noteCount")!=null){
			 displayCountNotes=(int)request.getSession().getAttribute("noteCount");
		}
		
		model.addAttribute("type", type);
		model.addAttribute("personalAllow", personalAllowed);
		model.addAttribute("meetingAllow", meetingAllowed);
		model.addAttribute("tutorialAllow", tutorialAllowed);
		model.addAttribute("maxWeeks", maxWeeks);
		model.addAttribute("role", role);
		model.addAttribute("groupId", groupId);
		model.addAttribute("displayCountNotes", displayCountNotes);
		
		return "events";
	}

	@RequestMapping("/setgroupview")
	public String createEvents(Model model, Principal p, HttpServletRequest request) {
		int newGroupId=0;
		
		/* get the groupid from the group controller which is stored in session
		 * check if the variable have been activated in session
		 */
		if(request.getSession().getAttribute("newGroupId")!=null){
			 newGroupId=(int)request.getSession().getAttribute("newGroupId");
		}
		System.out.println("newGroupId: "+newGroupId);//testing print
		
		String username = p .getName();
		User user =service.getUser(username);
		String role = user.getRoleId();
		
		/* the newgroupId is got from the group controller*/
		groupId = newGroupId;

		/* allow all type of events to be booked */
		type = 1;
		
		model.addAttribute("type", type);
		model.addAttribute("personalAllow", personalAllowed);
		model.addAttribute("meetingAllow", meetingAllowed);
		model.addAttribute("tutorialAllow", tutorialAllowed);
		model.addAttribute("maxWeeks", maxWeeks);
		model.addAttribute("role", role);
		model.addAttribute("groupId", groupId);
		model.addAttribute("displayCountNotes", displayCountNotes);
	

		return "events";
	}

	@RequestMapping("/setpersonalview")
	public String viewPersonalEvents(Model model) {
		/* only allow personal events to be booked */
		type = 0;
		groupId = 0;
		
		model.addAttribute("type", type);
		model.addAttribute("personalAllow", personalAllowed);
		model.addAttribute("meetingAllow", meetingAllowed);
		model.addAttribute("tutorialAllow", tutorialAllowed);
		model.addAttribute("maxWeeks", maxWeeks);
		model.addAttribute("groupId", groupId);
		model.addAttribute("displayCountNotes", displayCountNotes);
		
		return "events";
	}

	@RequestMapping(value = "/setform", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal,
			@RequestBody Map<String, Object> data) {

		/* get the data for form and get the availability of timeslot */
		String username = principal.getName();
		String numWeeks = (String) data.get("numWeeks");
		int allowWeeks = (Integer) data.get("allowWeeks");
		String end = (String) data.get("end");
		String start = (String) data.get("start");
		String types = (String) data.get("types");
		int weeks = Integer.parseInt(numWeeks);
		int mins = Integer.parseInt(end);// convert string minute to int
		/*------------------*/

		/* reformat the string time/date to a Timestamp formay */
		String reformatted = "";
		SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			reformatted = output.format(input.parse(start));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp ts = Timestamp.valueOf(reformatted);
		/*-------------------*/

		/* run the availability test return a number of weels */
		System.out.println("");
		allowWeeks = checkPersonAvail(username, ts, mins, groupId, types);// change
																			// 0
																			// to
																			// a
																			// group
																			// Id
		System.out.println("person available for: " + allowWeeks);
		System.out.println("");

		int sendBackWeeks = 0;

		if (weeks < allowWeeks) {
			sendBackWeeks = weeks;
		} else {
			sendBackWeeks = allowWeeks;
		}

		System.out.println(data);// for testing

		Map<String, Object> rval = new HashMap<String, Object>();
		rval.put("success", true);
		rval.put("allowWeeks", sendBackWeeks);

		return rval;
	}

	@RequestMapping(value = "/deleteevent", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteEvent(Principal principal,
			@RequestBody Map<String, Object> data) {

		/* get the logged in users name */
		String username = principal.getName();

		int id = (Integer) data.get("sendId");
		System.out.println("got id: " + id);

		/* get the user event to be deleted */
		Events userEvent = service.getEvent(id);

		/* get the typeID which will be used to check the groupEvents */
		int typeId = userEvent.getTypeId();
		System.out.println("type id is: "+typeId);

		/* get the groupEvent if typeId is greater than 0 
		 * and delete the group event
		 */
		if (typeId != 0) {
			GroupEvent groupEvent = service.getGroupEvent(typeId);

			/* get the username who created the group event */
			String userCreatedEvent = groupEvent.getUserName();

			/* if the user created the group event let them delete it */
			if (username.equals(userCreatedEvent)) {
				/* delete the group event8 */
				service.deleteGroupEvent(typeId);
				/* delete the event from everyones personal events feed*/
				service.deleteEventWithID(typeId);
			}
		}

		/* delete the user event */
		service.deleteUserEvent(id);

		Map<String, Object> rval = new HashMap<String, Object>();
		rval.put("success", true);

		return rval;
	}

	@RequestMapping(value = "/createevent", method = RequestMethod.POST)
	public String createEvents(Model model,
			@RequestParam Map<String, String> params, Principal p)
			throws ParseException {

		model.addAttribute("type", type);
		model.addAttribute("personalAllow", personalAllowed);
		model.addAttribute("meetingAllow", meetingAllowed);
		model.addAttribute("tutorialAllow", tutorialAllowed);
		model.addAttribute("maxWeeks", maxWeeks);
		model.addAttribute("groupId", groupId);
		model.addAttribute("displayCountNotes", displayCountNotes);
		
		System.out.println("in group " + groupMembers);
		/* add the logged in user to the group */
		String username = p.getName();
		User user = service.getUser(username);
		groupMembers.add(user);

		System.out.println(params);
		String start = params.get("start");
		System.out.println(params);

		/* reformat the string time/date to a Timestamp format */
		String reformatted = "";
		SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			reformatted = output.format(input.parse(start));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp ts = Timestamp.valueOf(reformatted);

		/* format user input */
		int weeks = Integer.parseInt(params.get("recuramount"));
		int mins = Integer.parseInt(params.get("end"));
		long t = ts.getTime();
		long m = mins * 60 * 1000;
		Timestamp end = new Timestamp(t + m);// calcute end time of event

		/* if its a group event, create a group event */
		if (groupId != 0) {

			groupMembers = service.getUsersInGroup(groupId);
			System.out.println("in group " + groupMembers);
			GroupEvent groupEvent = new GroupEvent();

			groupEvent.setGroupId(groupId);
			groupEvent.setEventType(params.get("type"));
			groupEvent.setUserName(username);
			groupEvent.setStart(ts);
			groupEvent.setRecurring(weeks);
			groupEvent.setEnd(end);
			groupEvent.setTitle(params.get("title"));

			/* set type to groupEventId this id refers userEvents to groupEvents */
			type = service.createWithKey(groupEvent);
			System.out.println("the fucking type is " + type);
		}

		System.out.println(" Date " + ts);

		/*
		 * create an event for each user in the group can be just member in
		 * group, the logged in user
		 */
		for (User member : groupMembers) {

			String memberName = member.getUserName();
			/* set the event details */
			Events event = new Events();
			event.setTitle(params.get("title"));
			event.setStart(ts);
			event.setEventType(params.get("type"));
			event.setUserName(memberName);
			event.setTypeId(type);
			event.setRecurring(weeks);
			event.setEnd(end);
			
			/*notify everyone in group about the event*/
			Notification note = new Notification();
			note.setEventName(params.get("title"));
			note.setUserName(memberName);
			note.setCreatedBy(username);
			note.setTime(ts);
			service.createNotification(note);

			/* create the event */
			service.createEvent(event);
		}

		/* empty out the List for next create operation */
		groupMembers.clear();

		return "events";
	}

	@RequestMapping(value = "/eventselector", method = RequestMethod.POST)
	public String changeEventFeed(Model model,
			@RequestParam Map<String, String> params, Principal p) {
		String pick = params.get("choice");
		System.out.println(params);

		/* clean up this if else put into hashmap maybe is we hace time */

		if (pick.equals("meeting")) {

			meetIsSet = !meetIsSet;

			if (meetIsSet) {
				meetingAllowed = true;
				personalAllowed = perIsSet;
			} else {
				meetingAllowed = false;
				personalAllowed = false;
			}

		} else if (pick.equals("module")) {
			modIsSet = !modIsSet;

			if (modIsSet) {
				tutorialAllowed = true;
				personalAllowed = meetIsSet && perIsSet;
				meetingAllowed = meetIsSet;
			} else {
				tutorialAllowed = false;
				personalAllowed = false;
				meetingAllowed = false;
			}

		} else {
			perIsSet = !perIsSet;

			if (perIsSet) {
				personalAllowed = perIsSet & meetIsSet;
			} else {
				personalAllowed = false;
			}

		}

		/* add the model attributes after the logic is done */
		model.addAttribute("type", type);
		model.addAttribute("personalAllow", personalAllowed);
		model.addAttribute("meetingAllow", meetingAllowed);
		model.addAttribute("tutorialAllow", tutorialAllowed);
		model.addAttribute("maxWeeks", maxWeeks);
		model.addAttribute("groupId", groupId);
		model.addAttribute("displayCountNotes", displayCountNotes);

		/* print for testing */
		System.out.println("mod is: " + modIsSet);
		System.out.println("per is :" + perIsSet);
		System.out.println("meet is :" + meetIsSet);
		System.out.println("tutoialAllow is :" + tutorialAllowed);
		System.out.println("personalAllow is :" + personalAllowed);
		System.out.println("meetAllow is :" + meetingAllowed);

		return "events";// posibilily have a boolean to remember view mode

	}

	public int checkPersonAvail(String username, Timestamp time, int min,
			int groupId, String event) {
		int availableWeeks = 12;
		List<Events> e = null;

		for (int i = 12; i >= 1; i--) {

			if (sql.getFreeWeeks(username, time, i, min, groupId, event)
					.isEmpty() == false) {
				availableWeeks = i;
			}

		}

		// System.out.println(e);
		return availableWeeks;

	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getdata", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Object getMessages(Principal principal) {

		/*
		 * get group name id get calender type tp show like is it just personal
		 * calender, meeting calender or tutorial set the events to required
		 * type
		 */

		List<Stream> streams2 = null;
		User user = null;
		List<Events> events = null;
		if (principal == null) {
			streams2 = null;
		} else {

			String username = principal.getName();
			user = service.getUser(username);
			streams2 = service.getStreams();
			events = service.getUserEvents(username);

		}

		/* if group is not equal 0 show the groups view data */
		if (groupId != 0) {

			/* get user events from the selected group */
			events = service.getEventsWithGroupId(groupId);
		}

		ArrayList<Events> data = new ArrayList<>();

		/*
		 * need to check each event add events according to selector buttons add
		 * all events if all is selected
		 */

		ArrayList<Events> JSONFeed = new ArrayList<Events>();

		for (Events item : events) {

			String eventType = item.getEventType();

			if (meetIsSet && eventType.equals("meeting")) {// just add meetings
															// to json feed
				JSONFeed.add(item);
			} else if (modIsSet
					&& (eventType.equals("lecture") || eventType
							.equals("tutorial"))) {// just add meetings to json
													// feed
				JSONFeed.add(item);
			} else if (perIsSet && (eventType.equals("personal"))) {
				JSONFeed.add(item);
			}
		}

		for (Events item : JSONFeed) {
			/* set events color on json feed according to event type */
			Map<String, String> eventColors = new HashMap<String, String>() {
				{
					put("lecture", "green");
					put("tutorial", "#19FF19");
					put("meeting", "#33CCFF");
					put("personal", "#FF9900");
				}
			};

			String type = item.getEventType();
			String color = eventColors.get(type);
			item.setColor(color);

			for (int i = 0; i < item.getRecurring(); i++) {
				int id = item.getId();

				Calendar newStart = Calendar.getInstance();
				newStart.setTime(item.getStart());
				Calendar newEnd = Calendar.getInstance();
				newEnd.setTime(item.getEnd());
				newStart.add(Calendar.DAY_OF_WEEK, 7 * i);
				newEnd.add(Calendar.DAY_OF_WEEK, 7 * i);

				Events event = new Events();

				event.setId(id);
				event.setUserName(item.getUserName());
				event.setStart(new Timestamp(newStart.getTimeInMillis()));
				event.setEnd(new Timestamp(newEnd.getTimeInMillis()));
				event.setTitle(item.getTitle());
				event.setColor(item.getColor());

				data.add(event);

			}// end recur by 12

		}

		System.out.println(data);// print out json feed for testing
		JSONFeed = null;// reset the feed of data
		return data;/* return the data to be displayed in calendar*/

		/*
		 * come up with a feed for creating meetings group feed to check
		 * availablility return data as group feed
		 */

	}

}