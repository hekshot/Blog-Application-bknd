package com.skill.bucks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skill.bucks.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
