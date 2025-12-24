package com.tharaa.authService.dto;

public class RegisterResponse {

	private long userId;
	private String username;
	private String message;
	
	public RegisterResponse(long userId, String username, String message) {
		this.userId = userId;
		this.username = username;
		this.message = message;
	}

	public long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getMessage() {
		return message;
	}
	
	
}
