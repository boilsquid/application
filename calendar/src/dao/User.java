package dao;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;


public class User {
	
	public User() {
	
	}
	

	public User( String userName, String firstName, String lastName,
			String email, String phone, String roleId, int streamId,
			String password, String passwordConfirmation, Timestamp createdAt,
			Timestamp updatedAt, int signInCount, boolean enabled) {
		super();
		
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roleId = roleId;
		this.streamId = streamId;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.signInCount = signInCount;
		this.enabled = enabled;
	}



	private String userName;
	//@Size(min=5, max=100, message="Name must be between 5 and 100 characters.")
	private String firstName;
	//@Size(min=5, max=100, message="Name must be between 5 and 100 characters.")
	private String lastName;
	//@Email
	//@Pattern(regexp=".*\\@umail.*\\..*", message="This does not appear to be a valid ucc umail email address")
	private String email;
	private String phone;
	private String roleId;
	private int streamId;
	private String password;
	private String passwordConfirmation;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private int signInCount;
	private boolean enabled;
	



	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	public int getStreamId() {
		return streamId;
	}


	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public Timestamp getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}


	public int getSignInCount() {
		return signInCount;
	}


	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String toString() {
		return "User [ userName=" + userName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", roleId=" + roleId + ", streamId="
				+ streamId + ", password=" + password
				+ ", passwordConfirmation=" + passwordConfirmation
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", signInCount=" + signInCount + ", enabled=" + enabled + "]";
	}


	
	
}
