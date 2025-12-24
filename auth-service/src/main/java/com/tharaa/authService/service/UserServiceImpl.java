package com.tharaa.authService.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tharaa.authService.dto.LoginRequest;
import com.tharaa.authService.dto.LoginResponse;
import com.tharaa.authService.dto.RegisterRequest;
import com.tharaa.authService.dto.RegisterResponse;
import com.tharaa.authService.entity.User;
import com.tharaa.authService.exception.UserAlreadyExistsException;
import com.tharaa.authService.repository.UserRepository;
import com.tharaa.authService.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository; 
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public RegisterResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		
		if(userRepository.existsByUsername(request.getUsername())) {
			throw new UserAlreadyExistsException("Username already exists");
		}
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole("USER");
		
		User savedUser = userRepository.save(user);
		
		return new RegisterResponse(savedUser.getId(), 
				savedUser.getUsername(), 
				"User Registered Successfully");
	}

	@Override
	public LoginResponse login(LoginRequest request) {

		User user = userRepository.findByUsername(request.getUsername())
						.orElseThrow(() -> new RuntimeException("Invalid Username or Password"));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}
		
		String token = jwtUtil.generateToken(request.getUsername());
		
		return new LoginResponse(token, request.getUsername());
	}
}
