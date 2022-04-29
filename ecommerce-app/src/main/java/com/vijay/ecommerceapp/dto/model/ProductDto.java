package com.vijay.ecommerceapp.dto.model;

import javax.validation.constraints.NotNull;

public class ProductDto {
	
	private Long productId;
	@NotNull
	private String productName;
	private String description;
	@NotNull
	private Long price;
	@NotNull
	private String categoryName;
	@NotNull
	private Long quantity;
	
	public ProductDto() {
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
