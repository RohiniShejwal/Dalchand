package com.BikkadIT.ProjectByDurgesh.controllers;

import java.util.List;

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
import com.BikkadIT.ProjectByDurgesh.payloads.CategoryDto;
import com.BikkadIT.ProjectByDurgesh.services.CategoryServiceI;

@RestController
@RequestMapping(UriConstants.REQUEST_MAPPING_CATEGORY)
public class CategoryController {

	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryServiceI categoryServiceI;

	/**
	 * @author Rohini Shejwal
	 * @apiNote To create category in D/B
	 * @since 1.0
	 * @param Categorydto
	 * @return
	 */
	@PostMapping(UriConstants.CATEGORY_SLASH_URI)
	public ResponseEntity<CategoryDto> createCategory(@Validated @RequestBody CategoryDto categoryDto) {
		logger.info("Entering the request for create category");
		CategoryDto categoryDto1 = this.categoryServiceI.createCategory(categoryDto);
		logger.info("Completing the request for create category");
		return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.CREATED);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To update category in D/B
	 * @since 1.0
	 * @param Categorydto
	 * @return
	 */
	@PutMapping(UriConstants.CATEGORYS_ID)
	public ResponseEntity<CategoryDto> updateCategory(@Validated @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {
		logger.info("Entering the request for update category using categoryId{}:",categoryId);
		CategoryDto updateCategory = this.categoryServiceI.updateCategory(categoryDto, categoryId);
		logger.info("Completing the request for update category using categoryId{}:",categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To delete category in D/B
	 * @since 1.0
	 * @param Categorydto
	 * @return
	 */
	@DeleteMapping(UriConstants.CATEGORYS_ID)
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		logger.info("Entering the request for delete category using categoryId{}:",categoryId);
		this.categoryServiceI.deleteCategory(categoryId);
		logger.info("Completing the request for delete category using categoryId{}:",categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstant.DELETE_CATEGORY, true), HttpStatus.OK);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To get single category from D/B
	 * @since 1.0
	 * @param Categorydto
	 * @return
	 */
	@GetMapping(UriConstants.CATEGORYS_ID)
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
		logger.info("Entering the request for get category");
		CategoryDto category = this.categoryServiceI.getCategory(categoryId);
		logger.info("Completing the request for get category");
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}

	/**
	 * @author Rohini Shejwal
	 * @apiNote To gel all category from D/B
	 * @since 1.0
	 * @param Categorydto
	 * @return
	 */
	@GetMapping(UriConstants.CATEGORY_SLASH_URI)
	public ResponseEntity<List<CategoryDto>> gellAllCategory() {
		logger.info("Entering the request for get all category");
		List<CategoryDto> categories = this.categoryServiceI.getCategories();
		logger.info("Completing the request for get all category");
		return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
	}
}
