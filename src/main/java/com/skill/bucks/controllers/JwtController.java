package com.skill.bucks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.bucks.exceptions.CustomAuthenticationException;
import com.skill.bucks.payloads.JwtRequest;
import com.skill.bucks.payloads.JwtResponse;
import com.skill.bucks.payloads.UserDto;
import com.skill.bucks.security.JwtHelper;
import com.skill.bucks.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class JwtController {
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest request){
		
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtHelper.generateToken(userDetails);
		
		JwtResponse response = new JwtResponse();
		response.setToken(token);
		
		return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
		
	}

	private void authenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (AuthenticationException e) {
			throw new CustomAuthenticationException("Authentication failed: " + e.getMessage());
		}
		
		
	}
	
	//register new user api
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
		
	}
	
}
