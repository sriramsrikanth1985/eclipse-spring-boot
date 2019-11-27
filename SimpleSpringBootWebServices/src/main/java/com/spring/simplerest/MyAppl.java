package com.spring.simplerest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyAppl {
	
	public static void main(String[] args){
		SpringApplication.run(MyAppl.class, args);
	}
	
	@RequestMapping("/print1")
	public String hello(){
		return "This is from print method";
	}
}
