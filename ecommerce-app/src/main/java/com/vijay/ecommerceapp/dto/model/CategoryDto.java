package com.vijay.ecommerceapp.dto.model;

import javax.validation.constraints.NotNull;

public class CategoryDto {
	
	private Long categoryId;
	@NotNull
	private String categoryName;
	
	public CategoryDto() {
		
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

}
