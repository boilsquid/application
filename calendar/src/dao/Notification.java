package dao;

import java.sql.Timestamp;

public class Notification {
	
	private int id;
	private String createdBy;
	private Timestamp time;
	private String userName;
	private String eventName;
	private Timestamp createdAt;
	
	public Notification(){
		
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Timestamp getTime() {
		return time;
	}


	public void setTime(Timestamp time) {
		this.time = time;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	@Override
	public String toString() {
		return "Notification [id=" + id + ", createdBy=" + createdBy
				+ ", time=" + time + ", userName=" + userName + ", eventName="
				+ eventName + ", createdAt=" + createdAt + "]";
	}

	


	
	
	

	
	
}
