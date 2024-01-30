package com.BikkadIT.ProjectByDurgesh.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDto {

	private int id;
	
	@NotBlank
	@Size(min = 5, message = "Min size of image name is 5")
	private String content;
	
}
