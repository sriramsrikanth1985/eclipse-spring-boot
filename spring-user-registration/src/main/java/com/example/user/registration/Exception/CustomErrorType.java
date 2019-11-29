package com.example.user.registration.Exception;

import com.example.user.registration.dao.UserDAO;

public class CustomErrorType extends UserDAO {
	
	private String errorMessage;
	
	public CustomErrorType(final String message){
		this.errorMessage = message;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public CustomErrorType(UserDAO user, String message){
		this.setDAO(user);
		this.errorMessage = message;
	}

}
