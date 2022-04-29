package com.vijay.ecommerceapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="user")
public class UserEntity {
	
	@Id
	@GeneratedValue
	private Long userId;
	private String password;
	private String email;
	private String role;
	private String contact_no;
	private String address;
	
	@OneToOne(mappedBy="user",cascade=CascadeType.REMOVE)
	private CartEntity cart;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private List<OrderEntity> orders;
	
	public UserEntity() {
	
	}

//	public UserEntity(Long userId, String password) {
//		super();
//		this.userId = userId;
//		this.password = password;
//	}
	

	public UserEntity(Long userId, String password, String email, String role, String contact_no, String address) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.role = role;
		this.contact_no = contact_no;
		this.address = address;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CartEntity getCart() {
		return cart;
	}

	public void setCart(CartEntity cart) {
		this.cart = cart;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", password=" + password + ", email=" + email + ", role=" + role
				+ ", contact_no=" + contact_no + ", address=" + address + "]";
	}

//	@Override
//	public String toString() {
//		return "UserEntity [userId=" + userId + ", password=" + password + "]";
//	}
	
	


}
