package com.example.demo.model;

public class JwtRequest {
	
	String username;
	String password;
	
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public JwtRequest() {
	}

	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + "]";
	}
	
	

}
