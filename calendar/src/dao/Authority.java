package dao;

public class Authority {

	private int id;
	private String userName;
	private String authority;
	
	public Authority(){
		
	}
	
	public Authority(int id, String userName, String authority) {
		super();
		this.id = id;
		this.userName = userName;
		this.authority = authority;
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", userName=" + userName
				+ ", authority=" + authority + "]";
	}

}
