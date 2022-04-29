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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.ecommerceapp.dto.model.ProductDto;
import com.vijay.ecommerceapp.service.ProductService;
import com.vijay.ecommerceapp.util.Response;

@RestController
public class ProductController {
	
	static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService service;
	
	@PostMapping("/products")
	public Response<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
		log.info("CreateProduct method Called");
		return service.createProduct(productDto);
	}
	
	@GetMapping("/products/{productId}")
	public Response<ProductDto> findProduct(@PathVariable Long productId){
		log.info("FindProduct method Called");
		return service.findProduct(productId);
	}
	
	@GetMapping("/products")
	public Response<List<ProductDto>> findAllProduct(){
		log.info("FindallProduct method Called");
		return service.findAllProduct();
	}
	
	@GetMapping("/products/categories/{categoryName}")
	public Response<List<ProductDto>> findProductByCategory(@PathVariable String categoryName){
		log.info("FindProductByCategory method Called");
		return service.findAllProductByCategory(categoryName);
	}
	
	@PutMapping("/products/{productId}")
	public Response<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto,
			@PathVariable Long productId){
		log.info("UpdateProduct method Called");
		return service.updateProduct(productDto, productId);
	}
	
	@DeleteMapping("/products/{productId}")
	public Response<String> deleteproduct(@PathVariable Long productId){
		log.info("DeleteProduct method Called");
		return service.deleteProduct(productId);
	}

}
