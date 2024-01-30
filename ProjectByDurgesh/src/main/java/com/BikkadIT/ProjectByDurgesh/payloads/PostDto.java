package com.BikkadIT.ProjectByDurgesh.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.BikkadIT.ProjectByDurgesh.entities.Comment;

//import com.BikkadIT.ProjectByDurgesh.entities.Category;
//import com.BikkadIT.ProjectByDurgesh.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	
	@NotBlank
	@Size(min = 4, message = "Min size of post title is 4")
	private String title;

	@NotBlank
	@Size(min = 10, message = "Min size of post title is 10")
	private String content;
	
	@NotBlank
	@Size(min = 5, message = "Min size of image name is 5")
	private String imageName;
	
	@NotEmpty
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto> comments = new HashSet<>();

} 
 