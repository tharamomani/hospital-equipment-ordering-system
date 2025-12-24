package com.tharaa.authService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authTest")
public class AuthTestController {

	@GetMapping("/health")
	public String health() {
		return "Auth health Service is running.";
	}
}
