package com.vijay.ecommerceapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vijay.ecommerceapp.dto.model.OrderProductDto;
import com.vijay.ecommerceapp.dto.mapper.OrderMapper;
import com.vijay.ecommerceapp.dto.model.OrderDto;
import com.vijay.ecommerceapp.entity.CartEntity;
import com.vijay.ecommerceapp.entity.OrderEntity;
import com.vijay.ecommerceapp.entity.OrderProductEntity;
import com.vijay.ecommerceapp.entity.OrderStatus;
import com.vijay.ecommerceapp.entity.ProductEntity;
import com.vijay.ecommerceapp.entity.UserEntity;
import com.vijay.ecommerceapp.exception.ProductOutOfStockException;
import com.vijay.ecommerceapp.repository.CartProductRepository;
import com.vijay.ecommerceapp.repository.OrderProductRepository;
import com.vijay.ecommerceapp.repository.OrderRepository;
import com.vijay.ecommerceapp.repository.ProductRepository;
import com.vijay.ecommerceapp.repository.UserRepository;
import com.vijay.ecommerceapp.service.OrderService;
import com.vijay.ecommerceapp.util.Response;

@Service
public class OrderServiceImpl implements OrderService{
	
	static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderProductRepository orderProductRepository;
	
	@Autowired
	private CartProductRepository cartProductRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response<OrderDto> placedOrder(@Valid OrderProductDto orderProductDto, Long userId) {
		log.debug("Entering placedOrder endpoint");
		OrderProductEntity orderProduct = modelMapper.map(orderProductDto, OrderProductEntity.class);
		
		ProductEntity productEntity = productRepository.getById(orderProduct.getProductId());
		if(productEntity.getQuantity()<orderProduct.getQuantity()) {
			throw new ProductOutOfStockException("Product out of stock.");
		}
		productEntity.setQuantity(productEntity.getQuantity()-orderProduct.getQuantity());
		
		OrderEntity order = modelMapper.map(orderProductDto, OrderEntity.class);
		order.setUser(userRepository.getById(userId));
		order.setPlacedDate(new Date());
		order.setStatus(OrderStatus.PLACED);
		
		orderProduct.setOrder(order);
		orderProduct.setTotal(orderProduct.getPrice()*orderProduct.getQuantity());
		orderProductRepository.save(orderProduct);
		log.info("Product ordered successfully");
		List<OrderProductEntity> orderProducts = new ArrayList<>();
		orderProducts.add(orderProduct);
		order.setOrderProducts(orderProducts);
		productRepository.save(productEntity);
		Long sumOfTotal = orderProductRepository.sumOfTotal(order.getOrderId());
		log.info("Total amount of Order is: "+sumOfTotal);
		order.setTotalAmount(sumOfTotal);
		orderRepository.save(order);
		log.info("Order saved successfully");
		return new Response<>(HttpStatus.CREATED, 
				modelMapper.map(order, OrderDto.class));
	}

	@Override
	public Response<OrderDto> getOrder(Long userId, Long orderId) {
		log.debug("Entering getOrder endpoint");
		return new Response<>(HttpStatus.OK, 
				modelMapper.map(orderRepository.getById(orderId), OrderDto.class));
	}

	@Override
	public Response<OrderDto> placedOrderFromCart(OrderDto orderDto, Long userId) {
		log.debug("Entering getOrder endpoint");
		OrderEntity order = modelMapper.map(orderDto, OrderEntity.class);
		UserEntity user = userRepository.getById(userId);
		CartEntity cart = user.getCart();
		List<OrderProductEntity> orderProducts = 
				OrderMapper.toOrderProducts(cart.getCartProducts());
		
		List<ProductEntity> products = new ArrayList<>();
		orderProducts.forEach((orderProduct) -> {
			ProductEntity productEntity = productRepository.getById(orderProduct.getProductId());
			if(productEntity.getQuantity()<orderProduct.getQuantity()) {
				throw new ProductOutOfStockException("Product out of stock.Product id: "+productEntity.getProductId());
			}
			productEntity.setQuantity(productEntity.getQuantity()-orderProduct.getQuantity());
			products.add(productEntity);
			orderProduct.setOrder(order);
		});
		
		order.setTotalAmount(cart.getTotalAmount());
		order.setUser(user);
		order.setPlacedDate(new Date());
		order.setStatus(OrderStatus.PLACED);
		orderRepository.save(order);
		log.info("Order saved successfully");
		orderProductRepository.saveAll(orderProducts);
		log.info("All Order Product saved successfully");
		productRepository.saveAll(products);
		order.setOrderProducts(orderProducts);
		cart.setTotalAmount(null);
		cartProductRepository.deleteAll(cart.getCartProducts());
		log.info("All Cart Product deleted");
		return new Response<>(HttpStatus.CREATED, modelMapper.map(order, OrderDto.class));
	}

	@Override
	public Response<List<OrderDto>> getAllOrder(Long userId) {
		log.debug("Entering getAllOrder endpoint");
		List<OrderEntity> orders = userRepository.getById(userId).getOrders();
		log.info("Get all Order successfully");
		return new Response<>(HttpStatus.OK, OrderMapper.toOrderDtos(orders));
	}

	@Override
	public Response<String> cancelOrder(Long userId, Long orderId) {
		log.debug("Entering cancelOrder endpoint");
		OrderEntity order = orderRepository.getById(orderId);
		if(!order.getUser().getUserId().equals(userId)) {
			log.error("Permission denied for cancel order");
			return new Response<>(HttpStatus.FORBIDDEN, "Permission denied for cancel order");
		}
		log.info("Order status: "+order.getStatus());
		if(order.getStatus().equals(OrderStatus.PLACED)) {
			order.setStatus(OrderStatus.CANCELLED);
			order.setCancelledDate(new Date());
			orderRepository.save(order);
			log.info("Order cancelled successfully");
			List<OrderProductEntity> orderProducts = order.getOrderProducts();
			orderProducts.forEach(orderProduct -> {
				ProductEntity productEntity = productRepository.getById(orderProduct.getProductId());
				productEntity.setQuantity(productEntity.getQuantity()+orderProduct.getQuantity());
				productRepository.save(productEntity);
			});
			return new Response<>(HttpStatus.OK, "order cancelled");
		}
		return new Response<>(HttpStatus.OK, "You can't cancel order, because order "+order.getStatus());
	}

	@Override
	public Response<String> updateOrder(OrderProductDto orderProductDto,Long userId, Long orderId) {
		log.debug("Entering updateOrder endpoint");
		OrderEntity order = orderRepository.getById(orderId);
		if(!order.getStatus().equals(OrderStatus.PLACED)) {
			log.error("Permission denied for Update order");
			return new Response<>(HttpStatus.OK, "You can't Update order, because order "+order.getStatus());
		}
		OrderProductEntity orderProduct = modelMapper.map(orderProductDto, OrderProductEntity.class);
		
		OrderProductEntity orderProductEntity = 
				orderProductRepository.findByProductIdAndOrderOrderId(orderProductDto.getProductId(),orderId);
		log.info("Finding Order Product");
		if(orderProductEntity==null) {
			log.error("Product not found");
			return new Response<>(HttpStatus.OK, "Product not found");
		}
		
		ProductEntity productEntity = productRepository.getById(orderProduct.getProductId());
		Long quantity = productEntity.getQuantity()+orderProductEntity.getQuantity();
		if(quantity<orderProduct.getQuantity()) {
			throw new ProductOutOfStockException("Product out of stock.");
		}
		productEntity.setQuantity(quantity-orderProduct.getQuantity());
		
		orderProduct.setTotal(orderProduct.getPrice()*orderProduct.getQuantity());
		orderProduct.setOrder(order);
		orderProduct.setOrderProductId(orderProductEntity.getOrderProductId());
		orderProductRepository.save(orderProduct);
		log.info("Order Product Updated successfully");
		productRepository.save(productEntity);
		Long sumOfTotal = orderProductRepository.sumOfTotal(order.getOrderId());
		log.info("Total Amount of order is: "+sumOfTotal);
		order.setTotalAmount(sumOfTotal);
		orderRepository.save(order);
		log.info("Order Updated successfully");
		return new Response<>(HttpStatus.OK, "Order product Updated");
	}

}
