package com.BikkadIT.ProjectByDurgesh.services;

import java.util.List;

//import org.springframework.stereotype.Service;

import com.BikkadIT.ProjectByDurgesh.payloads.CategoryDto;

public interface CategoryServiceI {

//create
	CategoryDto createCategory(CategoryDto categoryDto);

//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

//delete
	void deleteCategory(Integer categoryid);

//get single
	CategoryDto getCategory(Integer categoryId);

//get all
	List<CategoryDto> getCategories();

}
