package com.BikkadIT.ProjectByDurgesh.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.ProjectByDurgesh.appConstants.ApiConstant;
import com.BikkadIT.ProjectByDurgesh.appConstants.UriConstants;
import com.BikkadIT.ProjectByDurgesh.payloads.ApiResponse;
import com.BikkadIT.ProjectByDurgesh.payloads.CategoryDto;
import com.BikkadIT.ProjectByDurgesh.payloads.CommentDto;
import com.BikkadIT.ProjectByDurgesh.services.CommentServiceI;

@RestController
@RequestMapping(UriConstants.REQUEST_MAPPING_COMMENT)
public class CommentController {

	Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private CommentServiceI commentServiceI;
	
	/**
	 * @author Rohini Shejwal
	 * @apiNote To create comment
	 * @param commentDto
	 * @param postId
	 * @return
	 */
	@PostMapping(UriConstants.POST_URI + UriConstants.POST_ID + UriConstants.COMMENT_URI)
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto commentDto,
		    @PathVariable Integer postId){
		logger.info("Entering the request for create comment");
		CommentDto createComment = this.commentServiceI.createComment(commentDto, postId);
		logger.info("completing the request for create comment");
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}
	
	/**
	 * @author Rohini Shejwal
	 * @apiNote To delete comment
	 * @param commentId
	 * @return
	 */
	@DeleteMapping(UriConstants.COMMENTID_URI)
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		logger.info("Entering the request for delete comment");
		this.commentServiceI.deleteComment(commentId);
		logger.info("completing the request for delete comment");
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstant.DELETE_COMMENT, true), HttpStatus.OK);
	}
	
	/**
	 * @author Rohini Shejwal
	 * @apiNote To update comment
	 * @param commentDto
	 * @param commentId
	 * @return
	 */
	@PutMapping(UriConstants.COMMENTID1_URI)
	public ResponseEntity<CommentDto> updateComment(@Validated @RequestBody CommentDto commentDto,
			@PathVariable Integer commentId){
		logger.info("Entering the request for update comment using commentId{}:",commentId);
		CommentDto updateComment = this.commentServiceI.updateComment(commentDto, commentId);
		logger.info("Completing the request for update comment using commentId{}:",commentId);
		return new ResponseEntity<CommentDto>(updateComment, HttpStatus.OK);
	}
	
	/**
	 * @author Rohini Shejwal
	 * @apiNote To get all comment
	 * @return
	 */
	@GetMapping(UriConstants.COMMENT_SLASH_URI )
	public ResponseEntity<List<CommentDto>> getAllComment(){
		logger.info("Entering the request for get all comments");
		List<CommentDto> comments = this.commentServiceI.getComments();
		logger.info("Completing the request for get all comments");
		return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);
	}
	
	/**
	 * @author Rohini Shejwal
	 * @apiNote To get single comment
	 * @param commentId
	 * @return
	 */
	@GetMapping(UriConstants.COMMENTID1_URI)
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId){
		logger.info("Entering the request for get single comment ");
		CommentDto commentById = this.commentServiceI.getCommentById(commentId);
		logger.info("Completing the request for get single comment");
		return new ResponseEntity<CommentDto>(commentById, HttpStatus.OK);
	}
}