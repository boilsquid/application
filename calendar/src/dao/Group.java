package dao;

import java.sql.Timestamp;

public class Group {

	private int groupId;
	private String groupName;
	private String createdBy;
	private String userName;
	private Timestamp dateCreated;

	public Group() {

	}

	
	public Group(int groupId, String groupName, String createdBy,
			String userName, Timestamp dateCreated) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.createdBy = createdBy;
		this.userName = userName;
		this.dateCreated = dateCreated;
	}
	
	

	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}


	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName
				+ ", createdBy=" + createdBy + ", userName=" + userName
				+ ", dateCreated=" + dateCreated + "]";
	}

	

}
