package com.skill.bucks.services;

import java.util.List;
import com.skill.bucks.payloads.PostDto;
import com.skill.bucks.payloads.PostResponse;

public interface PostService {

	//post
	PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
	
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//getAllPost
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	
	//get Single Post
	PostDto getPostById(Integer postId);
	
	//get All Post By Category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get All Post By User
	List<PostDto> getPostsByUser(Integer userId);
	
	//search post
	List<PostDto> searchPosts(String keyword);
}
