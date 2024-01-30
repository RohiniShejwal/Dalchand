package com.BikkadIT.ProjectByDurgesh.services.impl;

import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.ProjectByDurgesh.entities.Category;
import com.BikkadIT.ProjectByDurgesh.exceptions.ResourceNotFoundException;
import com.BikkadIT.ProjectByDurgesh.payloads.CategoryDto;
import com.BikkadIT.ProjectByDurgesh.repositories.CategoryRepository;
import com.BikkadIT.ProjectByDurgesh.services.CategoryServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryServiceI {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

// create category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		log.info("Initiating the dao call for create category");
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category save = this.categoryRepository.save(category);
		CategoryDto categoryDto2 = this.modelMapper.map(save, CategoryDto.class);
		log.info("Completing the dao call for create category");
		return categoryDto2;
	}

// update category
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		log.info("Initiating the dao call for update category with categoryId{} :", categoryId);
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryInfo(categoryDto.getCategoryInfo());

		Category save = this.categoryRepository.save(category);
		CategoryDto categoryDto2 = this.modelMapper.map(save, CategoryDto.class);
		log.info("Completing the dao call for update category with categoryId{} :", categoryId);
		return categoryDto2;
	}

// delete category
	@Override
	public void deleteCategory(Integer categoryid) {
		log.info("Initiating the dao call for delete category with categoryId{} :", categoryid);
		Category category = this.categoryRepository.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryid));
		log.info("Completing the dao call for delete category with categoryId{} :", categoryid);
		this.categoryRepository.delete(category);
	}

// get single category
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		log.info("Initiating the dao call for get category");
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		log.info("Completing the dao call for get category");
		return this.modelMapper.map(category, CategoryDto.class);
	}

// get all category
	@Override
	public List<CategoryDto> getCategories() {
		log.info("Initiating the dao call for get all category");
		List<Category> findAll = this.categoryRepository.findAll();
		List<CategoryDto> collect = findAll.stream().map(category -> this.modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
		log.info("Completing the dao call for get all category");
		return collect;
	}
}
