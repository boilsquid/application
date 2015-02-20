package dao;



public class GroupMember {
	
	private int groupMembersId;
	private int groupId;
	private String userName;
	
	public GroupMember(){
		
	}

	public GroupMember(int groupMembersId, int groupId, String userName) {
		super();
		this.groupMembersId = groupMembersId;
		this.groupId = groupId;
		this.userName = userName;
	}

	public int getGroupMembersId() {
		return groupMembersId;
	}

	public void setGroupMembersId(int groupMembersId) {
		this.groupMembersId = groupMembersId;
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

	@Override
	public String toString() {
		return "GroupMember [groupMembersId=" + groupMembersId + ", groupId="
				+ groupId + ", userName=" + userName + "]";
	}
	
	
}
