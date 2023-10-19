package com.skill.bucks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.bucks.payloads.ApiResponse;
import com.skill.bucks.payloads.CategoryDto;
import com.skill.bucks.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer cid){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, cid);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cid){
		this.categoryService.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
	}
}
