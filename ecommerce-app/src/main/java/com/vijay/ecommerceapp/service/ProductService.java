package com.vijay.ecommerceapp.service;

import java.util.List;

import com.vijay.ecommerceapp.dto.model.ProductDto;
import com.vijay.ecommerceapp.util.Response;

public interface ProductService {
	
	public Response<ProductDto> createProduct(ProductDto productDto);
	public Response<ProductDto> findProduct(Long productId);
	public Response<List<ProductDto>> findAllProduct();
	public Response<List<ProductDto>> findAllProductByCategory(String categoryName);
	public Response<ProductDto> updateProduct(ProductDto productDto,Long productId);
	public Response<String> deleteProduct(Long productId);

}
