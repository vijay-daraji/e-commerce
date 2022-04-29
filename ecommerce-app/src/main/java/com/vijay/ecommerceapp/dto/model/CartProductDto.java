package com.vijay.ecommerceapp.dto.model;

import javax.validation.constraints.NotNull;

public class CartProductDto {
	
	private Long cartProductId;
	@NotNull
	private Long productId;
	@NotNull
	private Long price;
	@NotNull
	private Long quantity;
	private Long total;
	
	public CartProductDto() {
		
	}

	public Long getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(Long cartProductId) {
		this.cartProductId = cartProductId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	

}
