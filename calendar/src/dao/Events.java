package dao;

import java.sql.Timestamp;

public class Events {

	public Events() {

	}

	private int id;
	private String userName;
	private Timestamp start;
	private Timestamp end;
	private String title;

	public Events(int id, String userName, Timestamp start,
			Timestamp end, String title) {
		super();
		this.id = id;
		this.userName = userName;
		this.start = start;
		this.end = end;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Events [id=" + id + ", userName=" + userName + ", startTime="
				+ start + ", endTime=" + end + ", title=" + title + "]";
	}
	
	

}
