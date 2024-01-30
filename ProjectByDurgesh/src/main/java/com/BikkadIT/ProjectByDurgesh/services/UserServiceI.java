package com.BikkadIT.ProjectByDurgesh.services;

import java.util.List;

//import com.BikkadIT.ProjectByDurgesh.entities.User;
import com.BikkadIT.ProjectByDurgesh.payloads.UserDto;

public interface UserServiceI {

// create
	UserDto createUser(UserDto userdto);

// update
	UserDto update(UserDto userdto, Integer userid);

//get user by Id
	UserDto getUserById(Integer userid);

// get all
	List<UserDto> getAllUsers();

// delete
	void deleteUser(Integer userid);

}
