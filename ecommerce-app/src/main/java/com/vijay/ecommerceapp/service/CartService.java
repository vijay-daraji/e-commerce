package com.vijay.ecommerceapp.service;


import com.vijay.ecommerceapp.dto.model.CartDto;
import com.vijay.ecommerceapp.dto.model.CartProductDto;
import com.vijay.ecommerceapp.util.Response;

public interface CartService {

	Response<CartDto> addToCart(CartProductDto cartProdutDto, Long userId);

	Response<CartDto> updateToCart(CartProductDto cartProdutDto, Long userId);

	Response<String> deleteToCart(Long userId, Long productId);

	Response<CartDto> getCart(Long userId);
	
}
