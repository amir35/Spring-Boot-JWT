package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Employee Project using JWT token";
	}

	@PostMapping("/addUser")
	public User signUpUser(@RequestBody User user) {
		return userDetailsService.signUpNewUser(user);
	}
	
	

}
