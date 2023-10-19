package com.skill.bucks.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto { // user Data Transfer Object it will send data to UserService interface
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message ="Username must be min of 4 character")
	private String name;
	
	@Email(message = "Email address is not Valid")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be of min 3 char and max 10 chars !!")
	// @Pattern(regexp = "")
	private String password;
	
	@NotEmpty
	private String about;
	
}
