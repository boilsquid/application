package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import dao.Authority;
import dao.AuthorityDao;
import dao.Events;
import dao.EventsDao;
import dao.Group;
import dao.GroupDao;
import dao.GroupEvent;
import dao.GroupEventDao;
import dao.GroupMember;
import dao.GroupMemberDao;
import dao.Lecture;
import dao.LectureDao;
import dao.Sql;
import dao.Stream;
import dao.StreamDao;
import dao.User;
import dao.UserDao;
import mailers.registrationMailer;

@Service("ServiceDao")
public class ServiceDao {

	private UserDao userDao;
	private StreamDao streamDao;
	private LectureDao lectureDao;
	private AuthorityDao authorityDao;
	private EventsDao eventsDao;
	private GroupDao groupDao;
	private GroupMemberDao groupMemberDao;
	private Sql sql;
	private GroupEventDao groupEventDao;
	private registrationMailer mailer;

	@Autowired
	public void setUserDao(UserDao userDao, StreamDao streamDao,
			LectureDao lectureDao, AuthorityDao authorityDao,
			EventsDao eventsDao, Sql sql, GroupMemberDao groupMemberDao,
			GroupEventDao groupEventDao, registrationMailer mailer, GroupDao groupDao ) {

		this.userDao = userDao;
		this.streamDao = streamDao;
		this.lectureDao = lectureDao;
		this.authorityDao = authorityDao;
		this.eventsDao = eventsDao;
		this.sql = sql;
		this.groupDao = groupDao;
		this.mailer = mailer;
		this.groupMemberDao = groupMemberDao;
		this.groupEventDao = groupEventDao;
	}

	/* get indivual object ie get a particular user */
	public User getUser(String id) {
		return userDao.getItem(id);
	}
	
	public User findUser(String column, String value){
		return userDao.findBy(column, value);
	}
	
	public boolean updateUser(User user){
		return userDao.update(user);
	}
	
	public Stream getStream(int streamId) {
		// TODO Auto-generated method stub
		return streamDao.getItem(streamId);
	}

	public Lecture getLecture(int lectureId) {
		return lectureDao.getItem(lectureId);
	}

	public Events getEvent(int id) {
		// TODO Auto-generated method stub
		return eventsDao.getItem(id);
	}
	
	public GroupEvent getGroupEvent(int id) {
		// TODO Auto-generated method stub
		return groupEventDao.getItem(id);
	}

	/* DAO get lists methods which can be used be the serviceDao object */
	public List<User> getUsers() {
		return userDao.getList();
	}

	public List<Stream> getStreams() {
		return streamDao.getList();
	}

	public List<Lecture> getLectures() {
		return lectureDao.getList();
	}

	public List<Events> getEvents() {
		return eventsDao.getList();
	}

	/* DAO create methods which can be used be the seviceDao object */
	public boolean createUser(User user) {
		return userDao.create(user);
	}

	public boolean createStream(Stream stream) {
		return streamDao.create(stream);
	}

	public boolean createLecture(Lecture lecture) {
		return lectureDao.create(lecture);
	}

	public boolean createAuthority(Authority authority) {
		return authorityDao.create(authority);
	}

	public boolean createEvent(Events event) {

		return eventsDao.create(event);

	}
	
	public boolean create(Group group) {
		return groupDao.create(group);
	}

	public boolean createGroupMember(GroupMember member) {

		return groupMemberDao.create(member);

	}

	public boolean createGroupEvent(GroupEvent event) {

		return groupEventDao.create(event);

	}
	
	/* returns the auto generated primary key*/
	public int createWithKey(GroupEvent event) {
		return groupEventDao.createWithKey(event);
	}
	
	/* returns the auto generated primary key*/
	public int createGroupWithKey(Group group) {
		return groupDao.createWithKey(group);
	}
	
	/* Registration Mail and Password Reset Mail */
	public void sendRegistrationMail(String to, String from, String subject,
			String msg) {
		mailer.sendMail(from, to, subject, msg);
	}
	
	public void passwordResetMail(String to, String from, String subject, String msg){
		mailer.passwordReset(to, from, subject, msg);
	}

	/* update operations */

	public boolean updateEvent(Events event) {
		return eventsDao.update(event);
	}
	
	/*crud delete operations*/
	public boolean deleteGroupEvent(int id) {
		return groupEventDao.delete(id);
	}
	
	
	public boolean deleteUserEvent(int id) {
		return eventsDao.delete(id);
	}

	/* extra sql other than crud operations */
	public List<Events> getUserEvents(String userName) {
		return sql.getUserEvents(userName);
	}

	/*
	 * Delete, from, where, generic type sql call
	 */
	public boolean deleteFromWhere(Object table, Object username,
			Object eventtype) {

		return sql.deleteFromWhere(table, username, eventtype);
	}
	
	/*delete a group event from usersEvents table*/
	public boolean deleteEventWithID(int typeId) {
		return sql.deleteEventWithID(typeId);
	}

	public boolean deleteFromGroupMembers(Object table, Object username,
			Object createdBy) {
		return sql.deleteFromGroupMembers(table, username, createdBy);
	}

	/* get a group by groupname */
	public Group getGroupByName(Object groupName) {

		return sql.getGroupByName(groupName);
	}

	/* update minutes in userevents end time */
	public boolean updateMinutes(Object id, int min, Timestamp start) {
		return sql.updateMinutes(id, min, start);
	}
	
	/* get a list of users in a group*/
	public List<User> getUsersInGroup(int groupId){
		return sql.getUsersInGroup(groupId);
	}
	
	/* get a list of groups created by the admin or the user*/
	public List<Group> getPersonalGroupList(String username) {
		return sql.getPersonalGroupList(username);
	}
	
	/* get a list of events for a group*/
	public List<Events> getEventsWithGroupId(int groupId) {
		return sql.getEventsWithGroupId(groupId);
	}

}
