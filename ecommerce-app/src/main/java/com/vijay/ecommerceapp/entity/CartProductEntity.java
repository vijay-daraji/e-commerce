package com.vijay.ecommerceapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="cart_product")
public class CartProductEntity {
	
	@Id
	@GeneratedValue
	private Long cartProductId;
	private Long productId;
	private Long price;
	private Long quantity;
	private Long total;
	
	@ManyToOne
	private CartEntity cart;

	public CartProductEntity() {
		
	}

	public CartProductEntity(Long cartProductId, Long productId, Long price, Long quantity, Long total) {
		super();
		this.cartProductId = cartProductId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
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

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartProductEntity [cartProductId=" + cartProductId + ", productId=" + productId + ", price=" + price
				+ ", quantity=" + quantity + ", total=" + total + "]";
	}
	

}
