package com.vijay.ecommerceapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.ecommerceapp.dto.model.CategoryDto;
import com.vijay.ecommerceapp.service.CategoryService;
import com.vijay.ecommerceapp.util.Response;

@RestController
public class CategoryController {
	
	static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService service;
	
	@PostMapping("/categories")
	public Response<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		log.info("CreateCategory method Called");
		return service.createCategory(categoryDto);
		
	}
	
	@GetMapping("/categories")
	public Response<List<CategoryDto>> findAllCategory(){
		log.info("FindCategory method Called");
		return service.findAllCategory();
		
	}
	
	@DeleteMapping("/categories/{categoryName}")
	public Response<String> deleteCategory(@PathVariable String categoryName){
		log.info("DeleteCategory method Called");
		return service.deleteCategory(categoryName);
	}

}
