package controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class Validation {

	/* Change Strings to suit specific college */
	private String studentEmailMustContain = "@umail.ucc.ie";
	private String lecturerEmailMustContain = "@ucc.ie";
	private String errorMessageCollegeEmail;
	public boolean isCollegeEmail(String email, String roleId) {

		if (roleId.equals("student")) {
			errorMessageCollegeEmail ="Email must contain "+studentEmailMustContain;
			return email.contains(studentEmailMustContain);
		}
		else{
			errorMessageCollegeEmail ="Email must contain "+lecturerEmailMustContain;
			return email.contains(lecturerEmailMustContain);
		}
	}
	
	public String getErrorMessageCollegeEmail() {
		return errorMessageCollegeEmail;
	}
	
	

}
