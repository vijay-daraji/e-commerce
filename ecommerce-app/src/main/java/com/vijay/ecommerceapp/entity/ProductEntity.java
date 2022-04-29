package com.vijay.ecommerceapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="product")
public class ProductEntity {
	
	@Id
	@GeneratedValue
	private Long productId;
	private String productName;
	private String description;
	private Long price;
	private Long quantity;
	
	@ManyToOne
	private CategoryEntity category;

	public ProductEntity() {
		
	}

	public ProductEntity(Long productId, String productName, String description, Long price, Long quantity,
		CategoryEntity category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
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

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductEntity [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + ", quantity=" + quantity + ", category=" + category + "]";
	}

}
