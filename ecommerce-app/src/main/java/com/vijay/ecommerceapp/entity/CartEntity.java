package com.vijay.ecommerceapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="cart")
public class CartEntity {
	
	@Id
	@GeneratedValue
	private Long cartId;
	
	@OneToOne
	private UserEntity user;
	
	@OneToMany(mappedBy="cart",cascade=CascadeType.REMOVE)
	private List<CartProductEntity> cartProducts;
	
	private Long totalAmount;
	
	public CartEntity() {
		
	}


	public CartEntity(Long cartId, Long totalAmount) {
		super();
		this.cartId = cartId;
		this.totalAmount = totalAmount;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<CartProductEntity> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProductEntity> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "CartEntity [cartId=" + cartId + ", totalAmount=" + totalAmount + "]";
	}

	
}
