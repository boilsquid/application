package dao;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;



public class User {
	
	public User() {
	
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone
				+ ", roleId=" + roleId + ", streamId=" + streamId
				+ ", password=" + password + "]";
	}






	public User(String id, String firstName, String lastName, String email,
			String phone, String roleId, String streamId, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roleId = roleId;
		this.streamId = streamId;
		this.password = password;
	}
	
	






	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getStreamId() {
		return streamId;
	}
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}





	//@NotEmpty
	private String id;
	//@Size(min=5, max=100, message="Name must be between 5 and 100 characters.")
	private String firstName;
	private String lastName;
	//@Email
	//@Pattern(regexp=".*\\@umail.*\\..*", message="This does not appear to be a valid ucc umail email address")
	private String email;
	private String phone;
	private String roleId;
	private String streamId;
	private String password;

}
