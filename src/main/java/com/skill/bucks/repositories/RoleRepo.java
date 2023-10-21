package com.skill.bucks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skill.bucks.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
