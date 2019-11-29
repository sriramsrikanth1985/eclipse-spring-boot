package com.example.user.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringUserRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUserRegistrationApplication.class, args);
	}
	
	@RequestMapping("/helloSri")
	public String helloMethod(){
		return "Helo World and Dear Sriram Srikanth !";
	}

}
