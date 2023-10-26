package com.skill.bucks.payloads;

import lombok.Data;

@Data
public class JwtResponse {

	private String token;
	
	private UserDto user;
}
