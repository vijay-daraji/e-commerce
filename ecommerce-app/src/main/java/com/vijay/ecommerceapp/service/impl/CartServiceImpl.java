package com.vijay.ecommerceapp.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vijay.ecommerceapp.dto.model.CartDto;
import com.vijay.ecommerceapp.dto.model.CartProductDto;
import com.vijay.ecommerceapp.entity.CartEntity;
import com.vijay.ecommerceapp.entity.CartProductEntity;
import com.vijay.ecommerceapp.exception.ProductAlreadyExistsException;
import com.vijay.ecommerceapp.exception.ProductNotFoundException;
import com.vijay.ecommerceapp.repository.CartProductRepository;
import com.vijay.ecommerceapp.repository.CartRepository;
import com.vijay.ecommerceapp.repository.UserRepository;
import com.vijay.ecommerceapp.service.CartService;
import com.vijay.ecommerceapp.util.Response;

@Service
public class CartServiceImpl implements CartService{
	
	static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
			
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartProductRepository cartProductRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response<CartDto> addToCart(CartProductDto cartProdutDto, Long userId) {	
		log.debug("Entering addToCart endpoint");
		CartProductEntity cartProduct = modelMapper.map(cartProdutDto, CartProductEntity.class);
		CartEntity cart = userRepository.getById(userId).getCart();
		log.info("Get cart successfully, user id: "+userId);
		CartProductEntity cartProductEntity = cartProductRepository.
				findByProductIdAndCartId(cartProduct.getProductId(),cart.getCartId());
		log.info("Finding Product in cart");
		if(cartProductEntity==null) {	
			log.info("Product not available in cart");
			cartProduct.setTotal(cartProduct.getQuantity()*cartProduct.getPrice());
			cartProduct.setCart(cart);
			cartProductRepository.save(cartProduct);
			log.info("Product add in cart");
			cart.setTotalAmount(cartProductRepository.selectTotal(cart.getCartId()));
			cartRepository.save(cart);
			log.info("Cart updated successfully");
			return new Response<>(HttpStatus.CREATED, modelMapper.map(cartProduct.getCart(), CartDto.class));
		}
		log.error("Product already available in cart");
		throw new ProductAlreadyExistsException("Product already available in cart");
		
	}

	@Override
	public Response<CartDto> updateToCart(CartProductDto cartProdutDto, Long userId) {
		log.debug("Entering updateToCart endpoint");
		CartProductEntity cartProduct = modelMapper.map(cartProdutDto, CartProductEntity.class);
		CartEntity cart = userRepository.getById(userId).getCart();
		log.info("Get cart successfully");
		CartProductEntity cartProductEntity = cartProductRepository.
				findByProductIdAndCartId(cartProduct.getProductId(),cart.getCartId());
		log.info("Finding Product in cart");
		if(cartProductEntity==null) {
			log.info("Product not available in cart");
			throw new ProductNotFoundException("Product not available in cart");
		}
		cartProduct.setCartProductId(cartProductEntity.getCartProductId());
		cartProduct.setCart(cart);
		cartProduct.setTotal(cartProduct.getQuantity()*cartProduct.getPrice());
		cartProductRepository.save(cartProduct);
		log.info("Cart Product updated successfully");
		cart.setTotalAmount(cartProductRepository.selectTotal(cart.getCartId()));
		cartRepository.save(cart);
		log.info("Cart updated successfully");
		return new Response<>(HttpStatus.OK, modelMapper.map(cart, CartDto.class));
	}

	@Override
	public Response<String> deleteToCart(Long userId, Long productId) {
		log.debug("Entering deleteToCart endpoint");
		CartEntity cart = userRepository.getById(userId).getCart();
		log.info("Get cart successfully");
		CartProductEntity cartProductEntity = cartProductRepository.
				findByProductIdAndCartId(productId,cart.getCartId());
		log.info("Finding Product in cart");
		if(cartProductEntity==null) {
			log.info("Product not available in cart");
			return new Response<>(HttpStatus.OK, "Product not available in cart");	
		}
		cartProductRepository.delete(cartProductEntity);
		log.info("Cart Product deleted, product id: "+productId);
		cart.setTotalAmount(cartProductRepository.selectTotal(cart.getCartId()));
		cartRepository.save(cart);
		log.info("Cart updated successfully");
		return new Response<>(HttpStatus.OK, "Product removed from cart");
	}

	@Override
	public Response<CartDto> getCart(Long userId) {
		log.debug("Entering getCart endpoint");
		CartEntity cart = userRepository.getById(userId).getCart();
		log.info("Get cart successfully");
		return new Response<>(HttpStatus.OK, 
				modelMapper.map(cart, CartDto.class));
	}

}
