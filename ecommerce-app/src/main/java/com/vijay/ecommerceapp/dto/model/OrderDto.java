package com.vijay.ecommerceapp.dto.model;


import java.util.Date;
import java.util.List;

public class OrderDto {

	private Long orderId;
	private Long totalAmount;
	
	private String status;
	private Date placedDate;
	private Date confirmedDate;
	private Date cancelledDate;
	private Date deliveredDate;
	
	private Long userId;
	private List<OrderProductDto> orderProducts;
	
	public OrderDto() {
		
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public Date getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderProductDto> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductDto> orderProducts) {
		this.orderProducts = orderProducts;
	}

	
	
}
