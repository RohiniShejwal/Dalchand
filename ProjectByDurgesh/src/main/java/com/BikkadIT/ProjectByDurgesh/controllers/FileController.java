package com.BikkadIT.ProjectByDurgesh.controllers;


import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BikkadIT.ProjectByDurgesh.appConstants.UriConstants;
import com.BikkadIT.ProjectByDurgesh.payloads.PostDto;
import com.BikkadIT.ProjectByDurgesh.services.FileServiceI;
import com.BikkadIT.ProjectByDurgesh.services.PostService;

@RestController
@RequestMapping(UriConstants.REQUEST_MAPPING_IMAGE)
public class FileController {

	Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileServiceI fileServiceI;
	
	@Value("${project.image}")
	private String path;
	
	/**
	 * @author Rohini Shejwal
	 * @apiNote To upload post image
	 * @param image
	 * @param postId
	 * @return
	 * @throws IOException
	 */
	@PostMapping(UriConstants.IMAGE_URI + UriConstants.IMAGE_UPLOAD_URI + UriConstants.POST_ID)
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		logger.info("Entering the request for upload image");
		PostDto postDto = this.postService.getPostById(postId);
		
		String fileName = this.fileServiceI.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		logger.info("completing the request for upload image");
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }
	
	/**
	 * @author Rohini Shejwal
	 * @apiNote To get post image
	 * @param imageName
	 * @param response
	 * @throws IOException
	 */
	@GetMapping(UriConstants.IMAGE_URI + UriConstants.IMAGE_URI1 + UriConstants.IMAGE_NAME)//,produces= MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		
		logger.info("Entering the request for download image");
		InputStream resource = this.fileServiceI.getResource(path, imageName);
		response.setContentType(MediaType.ALL_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		logger.info("completing the request for download image");
	}
} 