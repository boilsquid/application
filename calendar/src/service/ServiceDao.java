package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import dao.Authority;
import dao.AuthorityDao;
import dao.Events;
import dao.EventsDao;
import dao.Lecture;
import dao.LectureDao;
import dao.Sql;
import dao.Stream;
import dao.StreamDao;
import dao.User;
import dao.UserDao;

@Service("ServiceDao")
public class ServiceDao {

	private UserDao userDao;
	private StreamDao streamDao;
	private LectureDao lectureDao;
	private AuthorityDao authorityDao;
	private EventsDao eventsDao;
	private Sql sql;

	@Autowired
	public void setUserDao(UserDao userDao, StreamDao streamDao,
			LectureDao lectureDao, AuthorityDao authorityDao, EventsDao eventsDao, Sql sql) {
		this.userDao = userDao;
		this.streamDao = streamDao;
		this.lectureDao = lectureDao;
		this.authorityDao = authorityDao;
		this.eventsDao = eventsDao;
		this.sql = sql;
	}

	/* get indivual object ie get a particular user */
	public User getUser(String id) {
		return userDao.getItem(id);
	}
	
	public Stream getStream(int streamId) {
		// TODO Auto-generated method stub
		return streamDao.getItem(streamId);
	}
	
	public Lecture getLecture(int lectureId){
		return lectureDao.getItem(lectureId);
	}
	
	public Events getEvent(int id) {
		// TODO Auto-generated method stub
		return eventsDao.getItem(id);
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
	
	/*update operations*/
	
	public boolean updateEvent(Events event) {
		return eventsDao.update(event);
	}
	
	/* extra sql other than crud operations*/
	public List<Events> getUserEvents(String userName) {
		return sql.getUserEvents(userName);
	}
	
	/*
	 * Delete, from, where,  generic type sql call
	 */
	public boolean deleteFromWhere(Object table, Object username, Object eventtype) {
	
		return sql.deleteFromWhere(table, username, eventtype);
	}

	
	

}
