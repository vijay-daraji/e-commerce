package com.vijay.ecommerceapp.dto.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDto {
	
	private Long userId;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String contact_no;
	@NotNull
	private String address;
	@NotNull
	private String role;
	
	public UserDto() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
