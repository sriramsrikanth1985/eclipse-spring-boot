package com.example.user.registration.Exception;

public class UserFoundException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public UserFoundException(String message) {
		this.message = message;
	}
	
	

}
