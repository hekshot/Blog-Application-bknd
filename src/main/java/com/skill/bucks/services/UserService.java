package com.skill.bucks.services;

import java.util.List;

import com.skill.bucks.payloads.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
}
