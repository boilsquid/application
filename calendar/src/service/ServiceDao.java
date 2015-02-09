package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Authority;
import dao.AuthorityDao;
import dao.Lecture;
import dao.LectureDao;
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

	@Autowired
	public void setUserDao(UserDao userDao, StreamDao streamDao,
			LectureDao lectureDao, AuthorityDao authorityDao) {
		this.userDao = userDao;
		this.streamDao = streamDao;
		this.lectureDao = lectureDao;
		this.authorityDao = authorityDao;
	}

	/* get indivual object ie get a particular user */
	public User getUser(String id) {
		return userDao.getItem(id);
	}
	
	public Stream getStream(int streamId) {
		// TODO Auto-generated method stub
		return streamDao.getItem(streamId);
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

	

}
