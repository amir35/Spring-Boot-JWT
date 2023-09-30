package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if(username.equals("amir")) {
//			return new User(username, "amir123", new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("User not authorized");
//		}
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), new ArrayList<>());
	}

	public User signUpNewUser(User user) {
		return userRepository.save(user);
	}

}
