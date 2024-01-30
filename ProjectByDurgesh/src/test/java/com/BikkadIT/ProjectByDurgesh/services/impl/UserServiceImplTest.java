package com.BikkadIT.ProjectByDurgesh.services.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.BikkadIT.ProjectByDurgesh.entities.User;
import com.BikkadIT.ProjectByDurgesh.payloads.UserDto;
import com.BikkadIT.ProjectByDurgesh.repositories.UserRepository;
import com.BikkadIT.ProjectByDurgesh.services.UserServiceI;

@SpringBootTest
class UserServiceImplTest {

	@Mock
	UserRepository repository;
	
	@Autowired
	UserServiceI userServiceI;
	
	
	@Test
	public void createUserTest() {
		
		
		User user = new User(1, "Rohini", "rohini@gmail.com", "12345", "Engineer", null);
		when(repository.save(any())).thenReturn(user);
		
		UserDto userDto = new UserDto(1, "Rohini", "rohini@gmail.com", "12345", "Engineer");
		UserDto createUser = userServiceI.createUser(userDto);
		
		assertEquals(createUser.getName(),user.getName());
	}

	@Test
	public void updateTest() {
		
		User user = new User(35, "Rohini", "rohini@gmail.com", "12345", "Engineer", List.of());
		when(repository.findById(any())).thenReturn(Optional.of(user));
		
		//when(repository.save(user)).thenReturn(user);
		
		UserDto userDto = this.userServiceI.update(new UserDto( 35,"balaji","bala@gmail.com","abc12345566767","police2333334566"), user.getId());
				
		when(repository.save(user)).thenReturn(user);
		
		assertNotEquals(user.getName(), userDto.getName());
//		assertNotEquals(user.getEmail(), userDto.getEmail());
//		assertEquals(user.getName(), "balaji");
		
	}
}

