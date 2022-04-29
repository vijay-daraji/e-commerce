package com.vijay.ecommerceapp.service;

import com.vijay.ecommerceapp.dto.model.OrderProductDto;

import java.util.List;

import javax.validation.Valid;

import com.vijay.ecommerceapp.dto.model.OrderDto;
import com.vijay.ecommerceapp.util.Response;

public interface OrderService {
	
	Response<OrderDto> placedOrder(@Valid OrderProductDto orderProductDto, Long userId);

//	Response<OrderDto> placedOrder(OrderProductDto orderProductDto,Long userId);

	Response<OrderDto> getOrder(Long userId, Long orderId);

	Response<OrderDto> placedOrderFromCart(OrderDto orderDto, Long userId);

	Response<List<OrderDto>> getAllOrder(Long userId);

	Response<String> cancelOrder(Long userId, Long orderId);

	Response<String> updateOrder(OrderProductDto orderProductDto,Long userId, Long orderId);

}
