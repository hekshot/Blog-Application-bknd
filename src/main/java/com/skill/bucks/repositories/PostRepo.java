package com.skill.bucks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skill.bucks.entities.Category;
import com.skill.bucks.entities.Post;
import com.skill.bucks.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
	
}
