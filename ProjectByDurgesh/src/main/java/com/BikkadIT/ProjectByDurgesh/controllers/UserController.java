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
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.ProjectByDurgesh.appConstants.ApiConstant;
import com.BikkadIT.ProjectByDurgesh.appConstants.UriConstants;
import com.BikkadIT.ProjectByDurgesh.payloads.ApiResponse;
import com.BikkadIT.ProjectByDurgesh.payloads.UserDto;
import com.BikkadIT.ProjectByDurgesh.services.UserServiceI;

@RestController
@RequestMapping(UriConstants.REQUEST_MAPPING_USER)
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserServiceI userServiceI;

	/**
	 * @author Rohini Shejwal
	 * @apiNote To create User in D/B
	 * @since 1.0
	 * @param Userdto
	 * @return
	 */
	@PostMapping(UriConstants.USER_SLASH_URI)
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		logger.info("Entering the request for create user");
		UserDto createUserdto = this.userServiceI.createUser(userDto);
		logger.info("Completing the request for create user");
		return new ResponseEntity<UserDto>(createUserdto, HttpStatus.CREATED);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To update User in D/B
	 * @since 1.0
	 * @param Userdto
	 * @return
	 */
	@PutMapping(UriConstants.USERS_ID)
	public ResponseEntity<UserDto> updateUser(@Validated @RequestBody UserDto userDto, @PathVariable Integer userid) {
		logger.info("Entering the request for update user with userid{} :", userid);
		UserDto update = this.userServiceI.update(userDto, userid);
		logger.info("Completing the request for update user with userid{} :", userid);
		return new ResponseEntity<UserDto>(update, HttpStatus.OK);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To delete User in D/B
	 * @since 1.0
	 * @param Userdto
	 * @return
	 */
	@DeleteMapping(UriConstants.USERS_ID)
	public ResponseEntity<ApiResponse> deteleUser(@PathVariable Integer userid) {
		logger.info("Entering the request for delete user with userid{} :", userid);
		this.userServiceI.deleteUser(userid);
		logger.info("Completing the request for delete user with userid{} :", userid);
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstant.DELETE_USER, true), HttpStatus.OK);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To get all User from D/B
	 * @since 1.0
	 * @param Userdto
	 * @return
	 */
	@GetMapping(UriConstants.USER_SLASH_URI)
	public ResponseEntity<List<UserDto>> getAllUser() {
		logger.info("Entering the request for get all users");
		logger.info("Completing the request for get all users");
		return ResponseEntity.ok(this.userServiceI.getAllUsers());
	}

	/**
	 * @author Rohini S
	 * @apiNote To get single User from D/B
	 * @since 1.0
	 * @param Userdto
	 * @return
	 */
	@GetMapping(UriConstants.USERS_ID)
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userid) {
		logger.info("Entering the request for get user");
		UserDto userById = this.userServiceI.getUserById(userid);
		logger.info("Completing the request for get user");
		return new ResponseEntity<UserDto>(userById, HttpStatus.OK);

	}
}
