package com.BikkadIT.ProjectByDurgesh.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 2, message = "Min size of user name is 2")
	private String name;
	
	@NotEmpty
	@Size(min = 10, message = "Min size of user email is 10")
	private String email;
	
	@NotBlank
	@Size(min = 8, message = "Min size of user password is 8")
	private String password;
	
	@NotEmpty
	@Size(min = 15, message = "Min size of about user is 15")
	private String about;
	
}
