package com.BikkadIT.ProjectByDurgesh.services;

import java.util.List;

import com.BikkadIT.ProjectByDurgesh.payloads.CategoryDto;
import com.BikkadIT.ProjectByDurgesh.payloads.CommentDto;
import com.BikkadIT.ProjectByDurgesh.payloads.PostDto;
import com.BikkadIT.ProjectByDurgesh.payloads.PostResponse;

public interface CommentServiceI {

// create
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
// delete
	void deleteComment(Integer commentId);
	
// update
	CommentDto updateComment(CommentDto commentDto, Integer commentId);
	
//get comment
	List<CommentDto> getComments();
		
//get single comment
	CommentDto getCommentById(Integer commentId);

	
}
