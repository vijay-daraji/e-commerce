package com.vijay.ecommerceapp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vijay.ecommerceapp.dto.mapper.OrderMapper;
import com.vijay.ecommerceapp.dto.model.OrderDto;
import com.vijay.ecommerceapp.entity.OrderEntity;
import com.vijay.ecommerceapp.entity.OrderProductEntity;
import com.vijay.ecommerceapp.entity.OrderStatus;
import com.vijay.ecommerceapp.entity.ProductEntity;
import com.vijay.ecommerceapp.repository.OrderRepository;
import com.vijay.ecommerceapp.repository.ProductRepository;
import com.vijay.ecommerceapp.service.AdminService;
import com.vijay.ecommerceapp.util.Response;

@Service
public class AdminServiceImpl implements AdminService{
	
	static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Response<String> confirmOrder(Long orderId) {
		log.debug("Entering confirmOrder endpoint");
		OrderEntity order = orderRepository.getById(orderId);
		log.info("Order Found with id "+orderId);
		log.info("Order status: "+order.getStatus());
		if(order.getStatus().equals(OrderStatus.PLACED)) {
			order.setStatus(OrderStatus.CONFIRMED);
			order.setConfirmedDate(new Date());
			orderRepository.save(order);
			log.info("Order confirmed successfully");
			return new Response<>(HttpStatus.OK, "order confirmed");
		}
		log.error("Order not confirm");
		return new Response<>(HttpStatus.OK, "You can't confirm order, because order "+order.getStatus());
	}

	@Override
	public Response<String> cancelOrder(Long orderId) {
		log.debug("Entering cancelOrder endpoint");
		OrderEntity order = orderRepository.getById(orderId);
		log.info("Order Found with id "+orderId);
		log.info("Order status: "+order.getStatus());
		if(order.getStatus().equals(OrderStatus.PLACED)) {
			order.setStatus(OrderStatus.CANCELLED);
			order.setCancelledDate(new Date());
			orderRepository.save(order);
			log.info("Order cancelled successfully");
			List<OrderProductEntity> orderProducts = order.getOrderProducts();
			orderProducts.forEach((orderProduct)-> {
				ProductEntity productEntity = productRepository.getById(orderProduct.getProductId());
				productEntity.setQuantity(productEntity.getQuantity()+orderProduct.getQuantity());
				productRepository.save(productEntity);
			});
			return new Response<>(HttpStatus.OK, "order cancelled");
		}
		log.error("Order not cancel");
		return new Response<>(HttpStatus.OK, "You can't cancel order, because order "+order.getStatus());
	}

	@Override
	public Response<String> deliverOrder(Long orderId) {
		log.debug("Entering deliverOrder endpoint");
		OrderEntity order = orderRepository.getById(orderId);
		log.info("Order Found with id "+orderId);
		log.info("Order status: "+order.getStatus());
		if(order.getStatus().equals(OrderStatus.CONFIRMED)) {
			order.setStatus(OrderStatus.DELIVERED);
			order.setDeliveredDate(new Date());
			orderRepository.save(order);
			log.info("Order delivered successfully");
			return new Response<>(HttpStatus.OK, "order delivered");
		}
		else if(order.getStatus().equals(OrderStatus.PLACED)) {
			log.error("Order not confirm, becuse order "+order.getStatus());
			return new Response<>(HttpStatus.OK, "Order not confirm, please do that first,then deliver");
		}
		log.error("Order not deliver, because order "+order.getStatus());
		return new Response<>(HttpStatus.OK, "You can't deliver order, because order "+order.getStatus());
	}

	@Override
	public Response<List<OrderDto>> getAllPlacedOrderByDate(String date) {
		log.debug("Entering getAllPlacedOrderByDate endpoint");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date placed_date = formatter.parse(date);
			Long total_order = orderRepository.countByPlacedDate(placed_date);
			log.info("Total of order: "+total_order);
			List<OrderEntity> orders = orderRepository.findByPlacedDate(placed_date);
			log.info("Get all Orders successfully");
			return new Response<>(HttpStatus.OK, OrderMapper.toOrderDtos(orders));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response<List<OrderDto>> getAllPlacedOrderByDateRange(String startDate, String endDate) {
		log.debug("Entering getAllPlacedOrderByDateRange endpoint");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start_date = formatter.parse(startDate);
			Date end_date = formatter.parse(endDate);
			Long total_order = orderRepository.countByPlacedDateBetween(start_date,end_date);
			log.info("Total of order: "+total_order);
			List<OrderEntity> orders = orderRepository.findByPlacedDateBetween(start_date,end_date);
			log.info("Get all Orders successfully");
			return new Response<>(HttpStatus.OK, OrderMapper.toOrderDtos(orders));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Response<String> getTotalAmountOfWeek(int week, int month, int year) {
		log.debug("Entering getTotalAmountOfWeek endpoint");
		LocalDate startDate;
		LocalDate endDate;
		
		if(week==1) {
			startDate = LocalDate.of(year, month, 1);
			endDate = LocalDate.of(year, month, 7);
		}
		else if(week==2) {
			startDate = LocalDate.of(year, month, 8);
			endDate = LocalDate.of(year, month, 14);
		}
		else if(week==3) {
			startDate = LocalDate.of(year, month, 15);
			endDate = LocalDate.of(year, month, 21);
		}
		else if(week==4) {
			startDate = LocalDate.of(year, month, 22);
			endDate = LocalDate.of(year, month, 28);
		}
		else if(week==5) {
			if(month==2) {
				log.error("You entered invalid week for this month");
				return new Response<>(HttpStatus.OK, "Please enter valid week for this month.");
			}
			startDate = LocalDate.of(year, month, 29);
			endDate = LocalDate.of(year, month, startDate.lengthOfMonth());
		}
		else {
			log.error("You entered invalid week for this month");
			return new Response<>(HttpStatus.OK, "Please enter valid week for this month.");
		}
		Date start_date = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date end_date = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Long sumOfTotalAmount = orderRepository.sumOfTotalAmount(start_date, end_date);
		log.info("Total Amount of Orders is: "+sumOfTotalAmount);
		return new Response<>(HttpStatus.OK, "Total Amount of the Week is "+sumOfTotalAmount);
	}

	@Override
	public Response<String> getTotalAmountOfMonth(int month, int year) {
		log.debug("Entering getTotalAmountOfMonth endpoint");
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = LocalDate.of(year, month, startDate.lengthOfMonth());
		Date start_date = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date end_date = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Long sumOfTotalAmount = orderRepository.sumOfTotalAmount(start_date, end_date);
		log.info("Total Amount of Orders is: "+sumOfTotalAmount);
		return new Response<>(HttpStatus.OK, "Total Amount of the Month is "+sumOfTotalAmount);
	}

	@Override
	public Response<String> getTotalAmountOfQuarter(int quarter, int year) {
		log.debug("Entering getTotalAmountOfQuarter endpoint");
		LocalDate startDate;
		LocalDate endDate;
		
		if(quarter==1) {
			startDate = LocalDate.of(year, 1, 1);
			endDate = LocalDate.of(year, 3, 31);
		}
		else if(quarter==2) {
			startDate = LocalDate.of(year, 4, 1);
			endDate = LocalDate.of(year, 6, 30);
		}
		else if(quarter==3) {
			startDate = LocalDate.of(year, 7, 1);
			endDate = LocalDate.of(year, 9, 30);
		}
		else if(quarter==4) {
			startDate = LocalDate.of(year, 10, 1);
			endDate = LocalDate.of(year, 12, 31);
		}
		else {
			log.error("You entered Invalid Quarter");
			return new Response<>(HttpStatus.OK, "Please enter valid Quarter.");
		}
		Date start_date = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date end_date = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Long sumOfTotalAmount = orderRepository.sumOfTotalAmount(start_date, end_date);
		log.info("Total Amount of Orders is: "+sumOfTotalAmount);
		return new Response<>(HttpStatus.OK, "Total Amount of the Quarter is "+sumOfTotalAmount);
	}

}
