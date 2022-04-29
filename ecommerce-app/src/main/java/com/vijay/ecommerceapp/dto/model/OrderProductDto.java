package com.vijay.ecommerceapp.dto.model;

import javax.validation.constraints.NotNull;

public class OrderProductDto {
	
	private Long orderProductId;
	@NotNull
	private long productId;
	@NotNull
	private Long price;
	@NotNull
	private Long quantity;
	private Long total;
	
	public OrderProductDto() {
		
	}

	public Long getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
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
