package com.tharaa.authService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tharaa.authService.dto.LoginRequest;
import com.tharaa.authService.dto.LoginResponse;
import com.tharaa.authService.dto.RegisterRequest;
import com.tharaa.authService.dto.RegisterResponse;
import com.tharaa.authService.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/health")
	public String health() {
		return "Auth health Service is running.";
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
		return userService.register(request);
	}
	
	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody LoginRequest request) {
		return userService.login(request);
	}
}
