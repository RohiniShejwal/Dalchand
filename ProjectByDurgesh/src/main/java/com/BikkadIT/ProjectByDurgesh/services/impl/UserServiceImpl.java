package com.BikkadIT.ProjectByDurgesh.services.impl;

import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

import com.BikkadIT.ProjectByDurgesh.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.BikkadIT.ProjectByDurgesh.entities.User;
import com.BikkadIT.ProjectByDurgesh.payloads.UserDto;
import com.BikkadIT.ProjectByDurgesh.repositories.UserRepository;
import com.BikkadIT.ProjectByDurgesh.services.UserServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private ModelMapper modelMapper;

	// create user
	@Override
	public UserDto createUser(UserDto userDto) {
//		this.modelMapper.map(userDto, User.class);
//		return null;

		log.info("Initiating the dao call for create user");
		User user = this.dtoToUser(userDto);
		User user2 = this.userRepository.save(user);
		log.info("Completing the dao call for create user");
		return this.userToDto(user2);
	}

// update user
	@Override
	public UserDto update(UserDto userdto, Integer userid) {
		log.info("Initiating the dao call for update user with userid{} :", userid);
		User user = this.userRepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));

		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());

		User updateduser = this.userRepository.save(user);
		UserDto userToDto = this.userToDto(updateduser);
		log.info("Completing the dao call for update user with userid{} :", userid);
		return userToDto;
	}

// get single user
	@Override
	public UserDto getUserById(Integer userid) {
		log.info("Initiating the dao call for get user");
		User user = this.userRepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
		log.info("Completing the dao call for get user");
		return this.userToDto(user);
	}

// get all users
	@Override
	public List<UserDto> getAllUsers() {
		log.info("Initiating the dao call for get all user");
		List<User> users = this.userRepository.findAll();
		List<UserDto> userdto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		log.info("Completing the dao call for get all user");
		return userdto;
	}

// delete user
	@Override
	public void deleteUser(Integer userid) {
		log.info("Initiating the dao call for delete user with userid{} :", userid);
		User user = this.userRepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
		log.info("Completing the dao call for delete user with userid{} :", userid);
		this.userRepository.delete(user);
	}

// Dto to user
	private User dtoToUser(UserDto userdto) {

		User user = new User();
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		return user;
	}

// user to Dto
	private UserDto userToDto(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
