package com.vijay.ecommerceapp.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vijay.ecommerceapp.dto.mapper.CategoryMapper;
import com.vijay.ecommerceapp.dto.model.CategoryDto;
import com.vijay.ecommerceapp.entity.CategoryEntity;
import com.vijay.ecommerceapp.exception.CategoryAlreadyExistsException;
import com.vijay.ecommerceapp.repository.CategoryRepository;
import com.vijay.ecommerceapp.service.CategoryService;
import com.vijay.ecommerceapp.util.Response;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response<CategoryDto> createCategory(CategoryDto categoryDto) {
		log.debug("Entering createCategory endpoint");
		CategoryEntity category = modelMapper.map(categoryDto, CategoryEntity.class);
		String categoryName = category.getCategoryName();
		if(categoryRepository.findByCategoryName(categoryName)==null) {
			log.info("Category not available, categoty name: "+categoryName);
			categoryRepository.save(category);
			log.info("Category created successfully");
		}
		else {
			log.error("Category already available, categoty name: "+categoryName);
			throw new CategoryAlreadyExistsException("Category Already Exist with name "+category.getCategoryName());
		}
		return new Response<>(HttpStatus.OK, modelMapper.map(category, CategoryDto.class));
	}

	@Override
	public Response<List<CategoryDto>> findAllCategory() {
		log.debug("Entering findAllCategory endpoint");
		List<CategoryEntity> categoryList = categoryRepository.findAll();
		log.info("get all category successfully");
		return new Response<>(HttpStatus.OK, CategoryMapper.toCategoryDtos(categoryList));
	}
	
	@Override
	public Response<String> deleteCategory(String categoryName) {
		log.debug("Entering deleteCategory endpoint");
		categoryRepository.deleteByCategoryName(categoryName);
		log.info("Category deleted, categoty name: "+categoryName);
		return new Response<>(HttpStatus.OK, "category deleted");
	}

}
