package dao;

public class Authority {

	private String userName;
	private String authority;
	
	public Authority(){
		
	}
	
	public Authority( String userName, String authority) {
		super();
	
		this.userName = userName;
		this.authority = authority;
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
		return "Authority [ userName=" + userName
				+ ", authority=" + authority + "]";
	}

}
