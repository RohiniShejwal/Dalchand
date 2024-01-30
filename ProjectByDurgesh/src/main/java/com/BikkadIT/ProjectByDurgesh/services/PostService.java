package com.BikkadIT.ProjectByDurgesh.services;

import java.util.List;

import com.BikkadIT.ProjectByDurgesh.entities.Post;
import com.BikkadIT.ProjectByDurgesh.payloads.PostDto;
import com.BikkadIT.ProjectByDurgesh.payloads.PostResponse;

public interface PostService {

//Create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
//delete
	void deletePost(Integer postId);
	
//get all posts
	PostResponse gelAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
//get single post
	PostDto getPostById(Integer postId);
	
//get all post by category
	//List<PostDto> getPostsByCategory(Integer categoryId);
	
//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
//search post
	List<Post> searchPost(String keyword);
}
