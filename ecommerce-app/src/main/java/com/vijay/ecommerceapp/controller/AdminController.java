package com.vijay.ecommerceapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.ecommerceapp.dto.model.OrderDto;
import com.vijay.ecommerceapp.service.AdminService;
import com.vijay.ecommerceapp.util.Response;

@RestController
public class AdminController {
	
	static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService service;
	
	@PutMapping("admins/orders/{orderId}/confirm")
	public Response<String> confirmOrder(@PathVariable Long orderId){
		log.info("ConfirmOrder method Called");
		return service.confirmOrder(orderId);
	}
	
	@PutMapping("admins/orders/{orderId}/cancel")
	public Response<String> cancelOrder(@PathVariable Long orderId){
		log.info("CancelOrder method Called");
		return service.cancelOrder(orderId);
	}
	
	@PutMapping("admins/orders/{orderId}/deliver")
	public Response<String> deliverOrder(@PathVariable Long orderId){
		log.info("DeliverOrder method Called");
		return service.deliverOrder(orderId);
	}
	
	@GetMapping("admins/orders/{date}")
	public Response<List<OrderDto>> getAllPlacedOrderByDate(@PathVariable String date){
		log.info("GetAllPlacedOrderByDate method Called");
		return service.getAllPlacedOrderByDate(date);
	}
	
	@GetMapping("admins/orders/{startDate}/{endDate}")
	public Response<List<OrderDto>> getAllPlacedOrderByDateRange(
			@PathVariable String startDate, @PathVariable String endDate){
		log.info("GetAllPlacedOrderByDateRange method Called");
		return service.getAllPlacedOrderByDateRange(startDate,endDate);
	}
	
	@GetMapping("admins/orders/week/{week}/{month}/{year}")
	public Response<String> getTotalAmountOfWeek(@PathVariable int week,
			@PathVariable int month, @PathVariable int year){
		log.info("GetTotalAmountOfWeek method Called");
		return service.getTotalAmountOfWeek(week, month, year);
	}
	
	@GetMapping("admins/orders/month/{month}/{year}")
	public Response<String> getTotalAmountOfMonth(@PathVariable int month,@PathVariable int year){
		log.info("GetTotalAmountOfMonth method Called");
		return service.getTotalAmountOfMonth(month, year);
	}
	
	@GetMapping("admins/orders/quarter/{quarter}/{year}")
	public Response<String> getTotalAmountOfQuarter(@PathVariable int quarter,@PathVariable int year){
		log.info("GetTotalAmountOfQuarter method Called");
		return service.getTotalAmountOfQuarter(quarter, year);
	}

}
