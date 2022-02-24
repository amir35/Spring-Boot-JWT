package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeContoller {
	
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Employee Project using JWT token";
	}
	
	

}
