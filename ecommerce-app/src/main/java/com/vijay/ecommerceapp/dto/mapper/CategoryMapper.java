package com.vijay.ecommerceapp.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.ecommerceapp.dto.model.CategoryDto;
import com.vijay.ecommerceapp.entity.CategoryEntity;

@Component
public class CategoryMapper {
	public static List<CategoryDto> toCategoryDtos(List<CategoryEntity> categoryList){
		
		List<CategoryDto> categoryDtos = 
				categoryList.stream().map(category -> 
					new ModelMapper().map(category, CategoryDto.class))
						.collect(Collectors.toList());
		
		return categoryDtos;
	}

}
