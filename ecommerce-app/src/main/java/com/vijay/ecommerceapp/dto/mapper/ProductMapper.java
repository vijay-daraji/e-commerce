package com.vijay.ecommerceapp.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.ecommerceapp.dto.model.ProductDto;
import com.vijay.ecommerceapp.entity.ProductEntity;

@Component
public class ProductMapper {
	
	public static List<ProductDto> toProductDtos(List<ProductEntity> productList){
		List<ProductDto> productDtos = productList.stream().map(product -> 
			new ModelMapper().map(product, ProductDto.class))
				.collect(Collectors.toList());
		return productDtos;
	}
	
}
