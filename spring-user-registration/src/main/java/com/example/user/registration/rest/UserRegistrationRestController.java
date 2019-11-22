package com.example.user.registration.rest;

import com.example.user.registration.dao.UserDAO;
import com.example.user.registration.repository.UserJpaRepository;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<List<UserDAO>> listAllUsers(){
		List<UserDAO> users = userJpaRepository.findAll();
		return new ResponseEntity<List<UserDAO>>(users, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDAO> createUser(@RequestBody final UserDAO user) {
		userJpaRepository.save(user);
		return new ResponseEntity<UserDAO>(user, HttpStatus.CREATED);
	}
}
