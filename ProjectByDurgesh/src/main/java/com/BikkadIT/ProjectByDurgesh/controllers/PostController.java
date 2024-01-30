package com.BikkadIT.ProjectByDurgesh.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.ProjectByDurgesh.appConstants.ApiConstant;
import com.BikkadIT.ProjectByDurgesh.appConstants.UriConstants;
import com.BikkadIT.ProjectByDurgesh.payloads.ApiResponse;
import com.BikkadIT.ProjectByDurgesh.payloads.PostDto;
import com.BikkadIT.ProjectByDurgesh.payloads.PostResponse;
import com.BikkadIT.ProjectByDurgesh.services.PostService;

@RestController
@RequestMapping(UriConstants.REQUEST_MAPPING_POST)
public class PostController {

	Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private PostService postService;
	
	
	/**
	 * @author Rohini Shejwal
	 * @apiNote To create post in D/B
	 * @since 1.0
	 * @param PostDto
	 * @return
	 */
	@PostMapping(UriConstants.USER_ID + UriConstants.CATEGORY_ID + UriConstants.POST_URI)
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		logger.info("Entering the request for create post");
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		logger.info("Completing the request for create post");
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To update post in D/B
	 * @since 1.0
	 * @param PostDto
	 * @return
	 */
	@PutMapping(UriConstants.POST_ID)
	public ResponseEntity<PostDto> updatePost(@Validated @RequestBody PostDto postDto, @PathVariable Integer postId) {
		logger.info("Entering the request for update post using postId{}:",postId);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		logger.info("Completing the request for update post using postId{}:",postId); 
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To delete post from D/B
	 * @since 1.0
	 * @param PostDto
	 * @return
	 */
	@DeleteMapping(UriConstants.POST_ID)
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		logger.info("Entering the request for delete post using postId{}:",postId);
		this.postService.deletePost(postId);
		logger.info("Completing the request for delete post using postId{}:",postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstant.DELETE_POST, true), HttpStatus.OK);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To get all posts from D/B
	 * @since 1.0
	 * @param PostDto
	 * @return
	 */
	@GetMapping(UriConstants.POST_SLASH_URI)
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "shortDir", defaultValue = "asc", required = false) String sortDir)
			{
		logger.info("Entering the request for get all post");
		PostResponse gelAllPost = this.postService.gelAllPost(pageNumber, pageSize, sortBy, sortDir);
		logger.info("Completing the request for get all post");
		return new ResponseEntity<PostResponse>(gelAllPost, HttpStatus.OK);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To get post by Id from D/B
	 * @since 1.0
	 * @param PostDto
	 * @return
	 */
	@GetMapping(UriConstants.POST_URI + UriConstants.POST_ID)
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		logger.info("Entering the request for get single post");
		PostDto postById = this.postService.getPostById(postId);
		logger.info("Completing the request for get single post");
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

//	/**
//	 * @author Rohini Shejwal
//	 * @apiNote To get posts by category from D/B
//	 * @since 1.0
//	 * @param PostDto
//	 * @return
//	 */
//  @GetMapping(UriConstants.CATEGORY_ID)
//	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
//	logger.info("Entering the request for get post by categoryId{}:",categoryId);	
//	List<PostDto> byCategory = this.postService.getPostsByCategory(categoryId);
//	logger.info("Completing the request for get post by category{}:",categoryId");	
//	return new ResponseEntity<List<PostDto>>(byCategory, HttpStatus.OK);
//	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To get posts by user post from D/B
	 * @since 1.0
	 * @param PostDto
	 * @return
	 */
	@GetMapping(UriConstants.USER_ID)
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		logger.info("Entering the request for get post by user{}:",userId);
		List<PostDto> byUser = this.postService.getPostsByUser(userId);
		logger.info("Completing the request for get post by user{}:",userId);
		return new ResponseEntity<List<PostDto>>(byUser, HttpStatus.OK);
	}

}
