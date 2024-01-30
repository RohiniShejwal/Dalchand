package com.BikkadIT.ProjectByDurgesh.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;
	
    @NotBlank
    @Size(min = 4, message = "Min size of category title is 4")
	private String categoryTitle;
	
	@NotBlank
    @Size(min = 10, message = "Min size of category info is 10")
	private String categoryInfo;
}
