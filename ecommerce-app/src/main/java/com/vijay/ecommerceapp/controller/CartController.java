package com.vijay.ecommerceapp.controller;

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

import com.vijay.ecommerceapp.dto.model.CartDto;
import com.vijay.ecommerceapp.dto.model.CartProductDto;
import com.vijay.ecommerceapp.service.CartService;
import com.vijay.ecommerceapp.util.Response;

@RestController
public class CartController {
	
	static final Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartService service;
	
	@PostMapping("cart/{userId}")
	public Response<CartDto> addToCart(@Valid @RequestBody CartProductDto cartProdutDto, 
			@PathVariable Long userId){
		log.info("AddToCart method Called");
		return service.addToCart(cartProdutDto, userId);
	}
	
	@PutMapping("cart/{userId}")
	public Response<CartDto> updateToCart(@Valid @RequestBody CartProductDto cartProdutDto,
			@PathVariable Long userId){
		log.info("UpdateToCart method Called");
		return service.updateToCart(cartProdutDto, userId);
	}
	
	@DeleteMapping("cart/{userId}/{productId}")
	public Response<String> deleteToCart(@PathVariable Long userId, @PathVariable Long productId){
		log.info("DeleteToCart method Called");
		return service.deleteToCart(userId, productId);
	}
	
	@GetMapping("cart/{userId}")
	public Response<CartDto> getCart(@PathVariable Long userId){
		log.info("GetCart method Called");
		return service.getCart(userId);
	}

}
