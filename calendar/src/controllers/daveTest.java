package controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;

import dao.Sql;
import dao.User;
import dao.UserDao;

public class daveTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Validation v = new Validation();
		
		/*test to see if email validation works*/
		/*if(v.isCollegeEmail("dave@umai.ucc.ie", "lecturer")==false){
			System.out.println(v.getErrorMessageCollegeEmail());
			System.out.println("email is not valid");
		}else{
			System.out.println(v.getErrorMessageCollegeEmail());
			System.out.println("email is valid");
		}*/
		
		
	}

}
