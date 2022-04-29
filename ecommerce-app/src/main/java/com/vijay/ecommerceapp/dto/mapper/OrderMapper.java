package com.vijay.ecommerceapp.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.vijay.ecommerceapp.dto.model.OrderDto;
import com.vijay.ecommerceapp.entity.CartProductEntity;
import com.vijay.ecommerceapp.entity.OrderEntity;
import com.vijay.ecommerceapp.entity.OrderProductEntity;


public class OrderMapper {
	
	public static List<OrderDto> toOrderDtos(List<OrderEntity> orderList){
		List<OrderDto> orderDtos = orderList.stream().map(order -> 
			new ModelMapper().map(order, OrderDto.class))
				.collect(Collectors.toList());
		return orderDtos;
	}
	
	public static List<OrderProductEntity> toOrderProducts(List<CartProductEntity> cartProductList){
		List<OrderProductEntity> orderProducts = cartProductList.stream().map(cartProduct -> 
			new ModelMapper().map(cartProduct, OrderProductEntity.class))
				.collect(Collectors.toList());
		return orderProducts;
	}
	
}
