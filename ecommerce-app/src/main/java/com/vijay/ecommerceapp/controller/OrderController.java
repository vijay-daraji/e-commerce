package com.vijay.ecommerceapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.ecommerceapp.dto.model.OrderProductDto;
import com.vijay.ecommerceapp.dto.model.OrderDto;
import com.vijay.ecommerceapp.service.OrderService;
import com.vijay.ecommerceapp.util.Response;

@RestController
public class OrderController {
	
	static final Logger log = LoggerFactory.getLogger(OrderController.class);
			
	@Autowired
	private OrderService service;
	
	@PostMapping("orders/users/{userId}/product/placed")
	public Response<OrderDto> placedOrder(@Valid @RequestBody OrderProductDto orderProductDto,
			@PathVariable Long userId){
		log.info("PlacedOrder method Called");
		return service.placedOrder(orderProductDto, userId);
	}
	
	@PostMapping("orders/users/{userId}/cart/placed")
	public Response<OrderDto> placedOrderFromCart(@Valid @RequestBody OrderDto orderDto,
			@PathVariable Long userId){
		log.info("PlacedOrderFromCart method Called");
		return service.placedOrderFromCart(orderDto, userId);
	}
	
	@GetMapping("orders/{orderId}/users/{userId}")
	public Response<OrderDto> getOrder(@PathVariable Long userId,@PathVariable Long orderId){
		log.info("GetOrder method Called");
		return service.getOrder(userId, orderId);
	}
	
	@GetMapping("orders/users/{userId}")
	public Response<List<OrderDto>> getAllOrder(@PathVariable Long userId){
		log.info("GetAllOrder method Called");
		return service.getAllOrder(userId);
	}
	
	@PutMapping("orders/{orderId}/users/{userId}/cancel")
	public Response<String> cancelOrder(@PathVariable Long userId,@PathVariable Long orderId){
		log.info("CancelOrder method Called");
		return service.cancelOrder(userId,orderId);
	}
	
	@PutMapping("orders/{orderId}/users/{userId}/update")
	public Response<String> updateOrder(@Valid @RequestBody OrderProductDto orderProductDto,
			@PathVariable Long userId,@PathVariable Long orderId){
		log.info("UpdateOrder method Called");
		return service.updateOrder(orderProductDto, userId, orderId);
	}
}
