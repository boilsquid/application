package dao;

import java.sql.Timestamp;

public class Events {

	public Events() {

	}
	


	private int id;
	private String userName;
	private Timestamp start;
	private Timestamp end;
	private int recurring;
	private String eventType;
	private int typeId;
	private String title;
	
	public Events(int id, String userName, Timestamp start, Timestamp end,
			int recurring, String eventType, int typeId, String title) {
		super();
		this.id = id;
		this.userName = userName;
		this.start = start;
		this.end = end;
		this.recurring = recurring;
		this.eventType = eventType;
		this.typeId = typeId;
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

	public int getRecurring() {
		return recurring;
	}

	public void setRecurring(int recurring) {
		this.recurring = recurring;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Events [id=" + id + ", userName=" + userName + ", start="
				+ start + ", end=" + end + ", recurring=" + recurring
				+ ", eventType=" + eventType + ", typeId=" + typeId
				+ ", title=" + title + "]";
	}


	
	
	
	

}
