package com.skill.bucks.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postid;
	
	@NotBlank
	@Size(min = 5, message = "title should be minimum of 5 character")
	private String title;
	
	@NotBlank
	@Size(min = 8, message = "content should be greater than 8 character")
	private String content;
	
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
