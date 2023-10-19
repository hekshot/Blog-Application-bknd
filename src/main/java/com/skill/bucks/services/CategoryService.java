package com.skill.bucks.services;

import java.util.List;

import com.skill.bucks.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto category);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//get All
	List<CategoryDto> getCategories();
}
