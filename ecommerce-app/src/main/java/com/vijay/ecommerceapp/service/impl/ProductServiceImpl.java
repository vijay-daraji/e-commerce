package com.vijay.ecommerceapp.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vijay.ecommerceapp.dto.mapper.ProductMapper;
import com.vijay.ecommerceapp.dto.model.ProductDto;
import com.vijay.ecommerceapp.entity.CategoryEntity;
import com.vijay.ecommerceapp.entity.ProductEntity;
import com.vijay.ecommerceapp.repository.CategoryRepository;
import com.vijay.ecommerceapp.repository.ProductRepository;
import com.vijay.ecommerceapp.service.ProductService;
import com.vijay.ecommerceapp.util.Response;

@Service
public class ProductServiceImpl implements ProductService{
	
	static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response<ProductDto> createProduct(ProductDto productDto) {
		log.debug("Entering createProduct endpoint");
		ProductEntity product = modelMapper.map(productDto, ProductEntity.class);
		String categoryName = productDto.getCategoryName();
		product.setCategory(categoryRepository.findByCategoryName(categoryName));
		productRepository.save(product);
		log.info("Product saved successfully");
		return new Response<>(HttpStatus.CREATED,modelMapper.map(product, ProductDto.class));
	}


	@Override
	public Response<ProductDto> findProduct(Long productId) {
		log.debug("Entering findProduct endpoint");
		return new Response<>(HttpStatus.OK,
				modelMapper.map(productRepository.getById(productId), ProductDto.class));
	}

	@Override
	public Response<List<ProductDto>> findAllProduct() {
		log.debug("Entering findAllProduct endpoint");
		List<ProductEntity> productList = productRepository.findAll();
		log.info("Get all product successfully");
		return new Response<>(HttpStatus.OK,ProductMapper.toProductDtos(productList));
	}

	@Override
	public Response<List<ProductDto>> findAllProductByCategory(String categoryName) {
		log.debug("Entering findAllProductByCategory endpoint");
		CategoryEntity category = categoryRepository.findByCategoryName(categoryName);
		log.info("Get all product by category name");
		return new Response<>(HttpStatus.OK,
				ProductMapper.toProductDtos(category.getProducts()));
	}
	
	@Override
	public Response<ProductDto> updateProduct(ProductDto productDto, Long productId) {
		log.debug("Entering updateProduct endpoint");
		ProductEntity product = modelMapper.map(productDto, ProductEntity.class);
		String categoryName = productDto.getCategoryName();
		product.setCategory(categoryRepository.findByCategoryName(categoryName));
		product.setProductId(productId);
		
		productRepository.save(product);
		log.info("Product Updated successfully");
		return new Response<>(HttpStatus.OK,modelMapper.map(product, ProductDto.class));
	}
	
	@Override
	public Response<String> deleteProduct(Long productId) {
		log.debug("Entering deleteProduct endpoint");
		productRepository.deleteById(productId);
		log.info("Product deleted, product id: "+productId);
		return new Response<>(HttpStatus.OK,"Product deleted");
	}

}
