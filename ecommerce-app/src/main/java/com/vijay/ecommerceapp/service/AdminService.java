package com.vijay.ecommerceapp.service;

import java.util.List;

import com.vijay.ecommerceapp.dto.model.OrderDto;
import com.vijay.ecommerceapp.util.Response;

public interface AdminService {
	
	public Response<String> confirmOrder(Long orderId);
	public Response<String> cancelOrder(Long orderId);
	public Response<String> deliverOrder(Long orderId);
	public Response<List<OrderDto>> getAllPlacedOrderByDate(String date);
	public Response<List<OrderDto>> getAllPlacedOrderByDateRange(String startDate, String endDate);
	public Response<String> getTotalAmountOfWeek(int week, int month, int year);
	public Response<String> getTotalAmountOfMonth(int month, int year);
	public Response<String> getTotalAmountOfQuarter(int quarter, int year);

}
