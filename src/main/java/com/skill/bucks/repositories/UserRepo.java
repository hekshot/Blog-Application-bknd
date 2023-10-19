package com.skill.bucks.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skill.bucks.entities.User;


// we are extending this interface to get pre-built functions from the JpaRepo like pagging,sorting, findall, delete and all
public interface UserRepo extends JpaRepository<User, Integer>{

	
	Optional<User> findByEmail(String email);
}
