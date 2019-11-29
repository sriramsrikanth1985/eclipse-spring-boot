package com.example.user.registration.service;

import java.util.List;

//business logic all goes here...
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.registration.Exception.CustomErrorType;
import com.example.user.registration.Exception.UserFoundException;
import com.example.user.registration.Exception.UserNotFoundException;
import com.example.user.registration.dao.UserDAO;
import com.example.user.registration.repository.UserJpaRepository;

@Service
public class UserRegistrationServiceImpl implements IUserRegistrationService {

	private UserJpaRepository userJpaRepository;
	
	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepo){
		this.userJpaRepository = userJpaRepo;
	}
	
	public List<UserDAO> getUsers(){
		List<UserDAO> users = userJpaRepository.findAll();
		if(users.isEmpty())
			throw new UserNotFoundException("No Users Found !!");
		else
			return users;
	}
	
	public UserDAO getUserBasedOnEmail(String email){
		UserDAO userFromDb;
		if( (userFromDb = userJpaRepository.findByEmailAddress(email)) != null ){
			System.out.println("getUserBasedOnEmail: Inside user found");
			return userFromDb;
		} else {
			throw new UserNotFoundException("The user: "+email+" does not exists.");
		}
	}
	
	public UserDAO createUser(UserDAO user){
		UserDAO userFromDB;
		if( (userFromDB = userJpaRepository.findByEmailAddress(user.getEmailAddress())) != null){
			System.out.println("createUser: Inside user found");
			throw new UserFoundException("The user: "+user.getEmailAddress()+" already exists.");
		}
		userJpaRepository.save(user);
		userFromDB = userJpaRepository.findByEmailAddress(user.getEmailAddress());
		return userFromDB;
	}
	
	public UserDAO updateUser(UserDAO user){
		UserDAO userFromDB;	
		if( (userFromDB = userJpaRepository.findByEmailAddress(user.getEmailAddress())) != null){
			System.out.println("updateUser:Inside user found");
			System.out.println("UpadateUser: userFromDB:: "+userFromDB.toString());
			userFromDB.setUsername(user.getUsername());
			userFromDB.setPassword(user.getPassword());
			userFromDB.setFirstname(user.getFirstname());
			userFromDB.setLastname(user.getLastname());
			userFromDB.setGender(user.getGender());
			userFromDB.setAge(user.getAge());
			userFromDB.setPhoneNum(user.getPhoneNum());
			userFromDB.setAddress(user.getAddress());
			userFromDB.setPincode(user.getPincode());
			//save will update as well
			userJpaRepository.save(userFromDB);
			return userFromDB;
		} else {
			System.out.println("UpdateUser:Inside user not found, hence saving");
			userJpaRepository.save(user);
			userFromDB = userJpaRepository.findByEmailAddress(user.getEmailAddress());
			return new CustomErrorType(userFromDB,"The user: "+user.getEmailAddress()+" does not exists, hence Added.");
		}
	}
	
	public UserDAO deleteUser(String email){
		UserDAO userFromDb;
		if( (userFromDb = userJpaRepository.findByEmailAddress(email)) != null ){
			System.out.println("deleteUser: Inside user found");
			userJpaRepository.delete(userFromDb);
			return userFromDb;
		} else {
			throw new UserNotFoundException("The user: "+email+" does not exists, hence can not be deleted.");
		}
	}
	
	public void deleteAllUsers(){
			System.out.println("Inside deleteAllUsers method");
			userJpaRepository.deleteAll();
	}
	
}
