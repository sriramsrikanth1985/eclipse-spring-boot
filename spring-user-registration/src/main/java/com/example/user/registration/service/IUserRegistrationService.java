package com.example.user.registration.service;

import java.util.List;
import com.example.user.registration.dao.UserDAO;

public interface IUserRegistrationService {
	
	   public abstract UserDAO createUser(UserDAO user);
	   public abstract UserDAO updateUser(UserDAO user);
	   public abstract UserDAO deleteUser(String emailAddress);
	   public abstract List<UserDAO> getUsers();
	   public abstract void deleteAllUsers();
	   
	   public UserDAO getUserBasedOnEmail(String email);

}
