package com.vijay.ecommerceapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="order_product")
public class OrderProductEntity {
	
	@Id
	@GeneratedValue
	private Long orderProductId;
	private Long productId;
	private Long price;
	private Long quantity;
	private Long total;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private OrderEntity order;

	public OrderProductEntity() {
		
	}
	
	public OrderProductEntity(Long orderProductId, Long productId, Long price, Long quantity, Long total,
			OrderEntity order) {
		super();
		this.orderProductId = orderProductId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
		this.order = order;
	}

	public Long getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
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

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderProductEntity [orderProductId=" + orderProductId + ", productId=" + productId + ", price=" + price
				+ ", quantity=" + quantity + ", total=" + total + ", order=" + order + "]";
	}
	

}
