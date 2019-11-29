package com.example.user.registration.Exception;

public class UserNotFoundException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public UserNotFoundException(String message) {
		this.message = message;
	}
	
	

}
