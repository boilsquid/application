package dao;

import java.sql.Timestamp;

public class GroupEvent {
	
	  
	private int groupEventId;
	private int groupId;
	private String userName;
	private Timestamp start;
	private Timestamp end;
	private int recurring;
	private String eventType;
	private String title;
	
	public GroupEvent(){
		
	}

	public GroupEvent(int groupEventId, int groupId, String userName,
			Timestamp start, Timestamp end, int recurring, String eventType,
			String title) {
		super();
		this.groupEventId = groupEventId;
		this.groupId = groupId;
		this.userName = userName;
		this.start = start;
		this.end = end;
		this.recurring = recurring;
		this.eventType = eventType;
		this.title = title;
	}

	public int getGroupEventId() {
		return groupEventId;
	}

	public void setGroupEventId(int groupEventId) {
		this.groupEventId = groupEventId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "GroupEvent [groupEventId=" + groupEventId + ", groupId="
				+ groupId + ", userName=" + userName + ", start=" + start
				+ ", end=" + end + ", recurring=" + recurring + ", eventType="
				+ eventType + ", title=" + title + "]";
	}

	
}
