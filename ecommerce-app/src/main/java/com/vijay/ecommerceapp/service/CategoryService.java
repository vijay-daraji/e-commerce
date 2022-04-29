package com.vijay.ecommerceapp.service;

import java.util.List;

import com.vijay.ecommerceapp.dto.model.CategoryDto;
import com.vijay.ecommerceapp.util.Response;

public interface CategoryService {

	Response<CategoryDto> createCategory(CategoryDto categoryDto);
	Response<String> deleteCategory(String categoryName);
	Response<List<CategoryDto>> findAllCategory();

}
