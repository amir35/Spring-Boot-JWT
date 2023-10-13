package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	//@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/welcome")
//	public String welcome() {
//		return "Welcome to spring boot tutorial";
//	}

	@GetMapping("/welcome")
	public ResponseEntity<Map<String, String>> welcome() {
		Map<String, String> response = new HashMap<>();
		response.put("message", "Welcome to spring boot tutorial");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/addUser")
	public User signUpUser(@RequestBody User user) {
		return userDetailsService.signUpNewUser(user);
	}
	
	

}
