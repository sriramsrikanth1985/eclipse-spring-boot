package com.example.user.registration.rest;

import com.example.user.registration.Exception.CustomErrorType;
import com.example.user.registration.dao.UserDAO;
import com.example.user.registration.repository.UserJpaRepository;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.*;
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

@RestController
@RequestMapping("/user-management")
public class UserRegistrationRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);
	
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepo){
		this.userJpaRepository = userJpaRepo;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDAO>> listAll(){
		List<UserDAO> users = userJpaRepository.findAll();
		if(users.isEmpty())
			return new ResponseEntity<List<UserDAO>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<UserDAO>>(users, HttpStatus.OK);
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
	
	@GetMapping(value="/users/{email}", consumes=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<UserDAO> getUserBasedOnEmail(@Valid @PathVariable("email") final String emailAddress){
		UserDAO userFromDb;
		if( (userFromDb = userJpaRepository.findByEmailAddress(emailAddress)) != null ){
			System.out.println("getUserBasedOnEmail: Inside user found");
			return new ResponseEntity<UserDAO>(userFromDb, HttpStatus.OK);
		} else {
			System.out.println("getUserBasedOnEmail: Inside user not found");
			return new ResponseEntity<UserDAO>(new CustomErrorType("The user: "+emailAddress+" does not exists."), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDAO> createUser(@Valid @RequestBody final UserDAO user) {
		UserDAO userFromDB;
		if( (userFromDB = userJpaRepository.findByEmailAddress(user.getUsername())) != null){
			System.out.println("createUser: Inside user found");
			return new ResponseEntity<UserDAO>(new CustomErrorType("The username: "+user.getUsername()+" already exists."), HttpStatus.FOUND);
		}
		userJpaRepository.save(user);
		return new ResponseEntity<UserDAO>(user, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/users/{email}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDAO> updateUser(@Valid @PathVariable("email") final String emailAddress,@Valid @RequestBody final UserDAO user) {
		UserDAO userFromDB;
		if( !emailAddress.equalsIgnoreCase(user.getEmailAddress()) )
			return new ResponseEntity<UserDAO>(new CustomErrorType("The email in url: "+emailAddress+" donot match with email in json message:"+user.getEmailAddress()+", hence not updated."), HttpStatus.NOT_FOUND);
		
		if( (userFromDB = userJpaRepository.findByEmailAddress(emailAddress)) != null){
			System.out.println("updateUser:Inside user found");
			System.out.println("userFromDB"+userFromDB.toString());
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
			return new ResponseEntity<UserDAO>(userFromDB, HttpStatus.FOUND);
		} else {
			System.out.println("updateUser:Inside user not found");
			return new ResponseEntity<UserDAO>(new CustomErrorType("The username: "+user.getUsername()+" is not found, hence not updated."), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/users/{email}", consumes=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<UserDAO> deleteUser(@Valid @PathVariable("email") final String emailAddress){
		UserDAO userFromDB;
		if( (userFromDB = userJpaRepository.findByEmailAddress(emailAddress)) != null){
			System.out.println("deleteUser: Inside delete method, user found");
			userJpaRepository.deleteById( userFromDB.getId() );
			return new ResponseEntity<UserDAO>(new CustomErrorType("User: "+emailAddress+" deleted"), HttpStatus.OK);
		} else {
			System.out.println("deleteUser: Inside user not found");
			return new ResponseEntity<UserDAO>(new CustomErrorType("User: "+emailAddress+" not found, hence not deleted"),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/deleteall")
	public ResponseEntity<UserDAO> deleteAll(){
		System.out.println("deleteall: Inside deleteall method");
		userJpaRepository.deleteAll();
		return new ResponseEntity<UserDAO>(new CustomErrorType("All users deleted !!"), HttpStatus.OK);
	}
}
