package controllers;



import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import service.ServiceDao;
import dao.Group;
import dao.GroupMember;
import dao.User;



@Controller
@SessionAttributes({"groups", "newGroupId", "users", "groupsToDelete"})
public class GroupController {

	ServiceDao service;
	int newGroupId=0;
	
	User activeUser = new User();
	
	String username="";
	
	List<User> users = new ArrayList<>();
	List<Group> groups = new ArrayList<>();
	List<Group> groupsToDelete = new ArrayList<>();
	
	@Autowired
	public GroupController(ServiceDao service) {
		this.service = service;
		/* if the user is logged in set username 
		 * the page is not accessable unless user is logged in
		 */
		
	}
	
	@RequestMapping("/groups")
	public String showGroups(Model model,Principal prin) {
		
		username = prin.getName();
		
		groups = service.getPersonalGroupList(username);
		System.out.println("personal groups are: "+groups);
		model.addAttribute("groups", groups);
		
		/*get a list of users not including logged in user*/
		users = service.getUsersOrdered(username);
		model.addAttribute("users", users);
		
		/* get a list of the groups created by user*/
		groupsToDelete = service.getGroupsOfUser(username);
		model.addAttribute("groupsToDelete", groupsToDelete);
		
		return "groups";
	}
	
	/* get a list of groups admin groups and groups created by users and let user pick group*/
	
	@RequestMapping(value = "/pickgroups", method = RequestMethod.POST)
	public ModelAndView pickGroups(Model model, @RequestParam Map<String, String> params) {
		
		System.out.println("pickgroups params: "+params);
		newGroupId = Integer.parseInt(params.get("groupId"));
		model.addAttribute("newGroupId", newGroupId);
		return new ModelAndView("forward:/setgroupview");
	}
	
	@RequestMapping(value = "/creategroups", method = RequestMethod.POST)
	public String createGroups(Model model, @RequestParam Map<String, String> params,@RequestParam("usernames") String[] userNames, Principal prin) {
		
	
		username = prin.getName();
		activeUser = service.getUser(username);
		
		System.out.println("pickgroups params: "+params);
		
		/* create the new group*/
		Group group = new Group();
		group.setGroupName(params.get("groupName"));
		group.setUserName(username);
		group.setCreatedBy(activeUser.getRoleId());
		System.out.println(group);
		int groupId= service.createGroupWithKey(group);
		
		/* put the user who created group into groupmembers*/
		GroupMember mainMember = new GroupMember();
		mainMember.setGroupId(groupId);
		mainMember.setUserName(username);
		service.createGroupMember(mainMember);
		 
		
		/* put the group into the group members table*/
		for(String item: userNames){
			/*handle empty user input from create group form*/
			if(!item.equals("no")){
			System.out.println(item);
			GroupMember member = new GroupMember();
			member.setGroupId(groupId);
			member.setUserName(item);
			service.createGroupMember(member);
			}
		}
		
		
		
		/*update the groups list*/
		groups = service.getPersonalGroupList(username);
		
		return "forward:/groups";
	}
	
	@RequestMapping(value = "/deletegroups", method = RequestMethod.POST)
	public String deleteGroups(Model model, @RequestParam Map<String, String> params,@RequestParam("groupDelete") int[] groupId, Principal prin) {
		
	
		username = prin.getName();
		activeUser = service.getUser(username);
		
		/* get a list of the groups created by user*/
		groupsToDelete = service.getGroupsOfUser(username);
		model.addAttribute("groupsToDelete", groupsToDelete);
		
		
		/*delete the group
		 * and delete the members in group
		 */
		for(int item: groupId){
			System.out.println("group id beiung deleted: "+item);//print for testing
		
			service.deleteGroupMembers(item);
			service.deleteGroup(item);
		}
		
		return "forward:/groups";
		
	}
}
