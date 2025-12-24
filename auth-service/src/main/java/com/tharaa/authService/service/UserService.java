package com.tharaa.authService.service;

import com.tharaa.authService.dto.LoginRequest;
import com.tharaa.authService.dto.LoginResponse;
import com.tharaa.authService.dto.RegisterRequest;
import com.tharaa.authService.dto.RegisterResponse;

public interface UserService {

	RegisterResponse register(RegisterRequest request);
	
	LoginResponse login(LoginRequest request);
}
