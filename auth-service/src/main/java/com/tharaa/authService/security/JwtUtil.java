package com.tharaa.authService.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final Key key;
	private final long jwtExpirationMs;

	/*
	 * Constructor that reads Expiration time from application.properties and get key from env variables
	 */
	public JwtUtil( @Value("${jwt.secretKey}") String secretKey, @Value("${jwt.expiration-ms}") long jwtExpirationMs ) {
		
		//String secretKey = System.getenv("JWT_SECRET");
		if(secretKey == null) {
			throw new IllegalStateException("JWT_SECRET is not set please check your env variables");
		}
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.jwtExpirationMs = jwtExpirationMs;
	}
	
	/*
	 * Generate JWT token with username and stored key in env variables using ES256 Algorithm
	 */
	public String generateToken(String username) {
	    return Jwts.builder()
	            .setSubject(username)
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
	            .signWith(key, SignatureAlgorithm.HS256) // ✅ use HS256 for HMAC key
	            .compact();
	}

}
