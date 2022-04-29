package com.vijay.ecommerceapp.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="order_details")
public class OrderEntity {
	
	@Id
	@GeneratedValue
	private Long orderId;
	private Long totalAmount;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	@Temporal(TemporalType.DATE)
	private Date placedDate;
	@Temporal(TemporalType.DATE)
	private Date confirmedDate;
	@Temporal(TemporalType.DATE)
	private Date cancelledDate;
	@Temporal(TemporalType.DATE)
	private Date deliveredDate;
	
	@ManyToOne
	private UserEntity user;
	
	
	@OneToMany(mappedBy="order",cascade=CascadeType.REMOVE)
	private List<OrderProductEntity> orderProducts;

	public OrderEntity() {
		
	}

	public OrderEntity(Long orderId, Long totalAmount, OrderStatus status, Date placedDate, Date confirmedDate,
			Date cancelledDate, Date deliveredDate) {
		super();
		this.orderId = orderId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.placedDate = placedDate;
		this.confirmedDate = confirmedDate;
		this.cancelledDate = cancelledDate;
		this.deliveredDate = deliveredDate;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<OrderProductEntity> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductEntity> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", placedDate=" + placedDate + ", confirmedDate=" + confirmedDate + ", cancelledDate=" + cancelledDate
				+ ", deliveredDate=" + deliveredDate + ", user=" + user + ", orderProducts=" + orderProducts + "]";
	}


}
