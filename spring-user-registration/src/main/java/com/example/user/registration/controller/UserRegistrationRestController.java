package com.example.user.registration.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.registration.Exception.CustomErrorType;
import com.example.user.registration.Exception.UserFoundException;
import com.example.user.registration.dao.UserDAO;
import com.example.user.registration.service.UserRegistrationServiceImpl;

@RestController
@RequestMapping("/user-management")
public class UserRegistrationRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);

	private UserRegistrationServiceImpl userRegistrationService;
	
	@Autowired
	public void setUserRepositoryService(UserRegistrationServiceImpl userRegistrationService){
		this.userRegistrationService = userRegistrationService;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDAO>> listAll(){
		List<UserDAO> users  = userRegistrationService.getUsers();
		return new ResponseEntity<List<UserDAO>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/users/{email}", consumes=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<UserDAO> getUserBasedOnEmail(@Valid @PathVariable("email") final String emailAddress){
		UserDAO userFromDb = userRegistrationService.getUserBasedOnEmail(emailAddress);
			return new ResponseEntity<UserDAO>(userFromDb, HttpStatus.OK);
	}
	
	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDAO> createUser(@Valid @RequestBody final UserDAO user) {
		UserDAO userFromDB = userRegistrationService.createUser(user);
			return new ResponseEntity<UserDAO>(new CustomErrorType(userFromDB,"The user: "+userFromDB.getEmailAddress()+" has been created."), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/users", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDAO> updateUser(@Valid @RequestBody final UserDAO user) {
		UserDAO userFromDB = userRegistrationService.updateUser(user);
		return new ResponseEntity<UserDAO>(userFromDB, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/users/{email}", consumes=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<CustomErrorType> deleteUser(@Valid @PathVariable("email") final String emailAddress){
		UserDAO userFromDB = userRegistrationService.deleteUser(emailAddress);
		return new ResponseEntity<CustomErrorType>(new CustomErrorType(userFromDB,"The user: "+emailAddress+" is deleted !!"), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/users/deleteall")
	public ResponseEntity<UserDAO> deleteAllUsers(){
		userRegistrationService.deleteAllUsers();
		return new ResponseEntity<UserDAO>(new CustomErrorType("All users deleted !!"), HttpStatus.OK);
	}
	
	/*	@PostMapping(value="/", consumes=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<UserDAO> getUserBasedOnEmail(@RequestBody final String uname){
		UserDAO userFromDb;
		if( (userFromDb = userJpaRepository.findByuname(uname)) != null ){
			System.out.println("Inside user found");
			return new ResponseEntity<UserDAO>(userFromDb, HttpStatus.OK);
		} else {
			System.out.println("Inside user not found");
			return new ResponseEntity<UserDAO>(userFromDb, HttpStatus.NOT_FOUND);
		}
	}*/
}
