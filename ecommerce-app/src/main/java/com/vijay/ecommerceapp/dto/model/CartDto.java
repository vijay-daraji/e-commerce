package com.vijay.ecommerceapp.dto.model;

import java.util.List;

public class CartDto {
	
	private Long cartId;
	private Long userId;
	private List<CartProductDto> cartProducts;
	private Long totalAmount;
	
	public CartDto() {
		
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<CartProductDto> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProductDto> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	

}
