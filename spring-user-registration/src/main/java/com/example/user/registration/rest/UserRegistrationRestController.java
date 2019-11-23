package com.example.user.registration.rest;

import com.example.user.registration.dao.UserDAO;
import com.example.user.registration.repository.UserJpaRepository;

import java.util.List;

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
@RequestMapping("/api/user")
public class UserRegistrationRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);
	
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepo){
		this.userJpaRepository = userJpaRepo;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDAO>> listAll(){
		List<UserDAO> users = userJpaRepository.findAll();
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
	
	@GetMapping(value="/{uname}", consumes=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<UserDAO> getUserBasedOnEmail(@PathVariable("uname") final String uname){
		UserDAO userFromDb;
		if( (userFromDb = userJpaRepository.findByuname(uname)) != null ){
			System.out.println("Inside user found");
			return new ResponseEntity<UserDAO>(userFromDb, HttpStatus.OK);
		} else {
			System.out.println("Inside user not found");
			return new ResponseEntity<UserDAO>(userFromDb, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDAO> createUser(@RequestBody final UserDAO user) {
		userJpaRepository.save(user);
		return new ResponseEntity<UserDAO>(user, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDAO> updateUser(@RequestBody final UserDAO user) {
		UserDAO userFromDB;
		if( (userFromDB = userJpaRepository.findByuname(user.getUname())) != null){
			System.out.println("Inside user found");
			System.out.println("userFromDB"+userFromDB.toString());
			userFromDB.setFname(user.getFname());
			userFromDB.setLname(user.getLname());
			userFromDB.setAge(user.getAge());
			userJpaRepository.save(userFromDB);
			return new ResponseEntity<UserDAO>(userFromDB, HttpStatus.FOUND);
		} else {
			System.out.println("Inside user not found");
			return new ResponseEntity<UserDAO>(user, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/", consumes=MediaType.TEXT_PLAIN_VALUE)
	public String deleteUser(@RequestBody final String uname){
		UserDAO userFromDB;
		if( (userFromDB = userJpaRepository.findByuname(uname)) != null){
			System.out.println("Inside delete method, user found");
		userJpaRepository.deleteById( userFromDB.getId() );
		return "User: "+uname+" deleted";
		} else {
			System.out.println("Inside user not found");
			return "User: "+uname+" not found, hence not deleted";
		}
	}
}
