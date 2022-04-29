package com.vijay.ecommerceapp.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vijay.ecommerceapp.dto.model.UserDto;
import com.vijay.ecommerceapp.dto.model.UserResponse;
import com.vijay.ecommerceapp.entity.CartEntity;
import com.vijay.ecommerceapp.entity.UserEntity;
import com.vijay.ecommerceapp.exception.UserAlreadyExistsException;
import com.vijay.ecommerceapp.repository.CartRepository;
import com.vijay.ecommerceapp.repository.UserRepository;
import com.vijay.ecommerceapp.service.UserService;
import com.vijay.ecommerceapp.util.Response;

@Service
public class UserServiceImpl implements UserService{
	
	static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Override
	public Response<UserResponse> createUser(UserDto userDto) {
		log.debug("Entering createUser endpoint");
		UserEntity user = modelMapper.map(userDto, UserEntity.class);
		UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		log.info("Finding user by email");
		if(userEntity==null) {
			log.info("User not available");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			log.info("User created successfully, user id: "+user.getUserId());
			CartEntity cart = modelMapper.map(userDto, CartEntity.class);
			cart.setUser(user);
			cartRepository.save(cart);
			log.info("Cart created for user");
			return new Response<>(HttpStatus.CREATED, modelMapper.map(user, UserResponse.class));
		}
		log.error("User already available");
		throw new UserAlreadyExistsException("User already exists with email: "+user.getEmail());
	}

	@Override
	public Response<UserResponse> findUser(Long userId) {
		log.debug("Entering findUser endpoint");
		return new Response<>(HttpStatus.OK, 
				modelMapper.map(userRepository.getById(userId), 
						UserResponse.class));
	}

	@Override
	public Response<UserResponse> UpdateUser(UserDto userDto, Long userId) {
		log.debug("Entering UpdateUser endpoint");
		UserEntity user = modelMapper.map(userDto, UserEntity.class);
		user.setUserId(userId);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
		log.info("User updated successfully, user id: "+userId);
		return new Response<>(HttpStatus.OK, modelMapper.map(user, UserResponse.class));
	}

	@Override
	public Response<String> deleteUser(Long userId) {
		log.debug("Entering deleteUser endpoint");
		userRepository.deleteById(userId);
		log.info("User deleted, user id: "+userId);
		return new Response<>(HttpStatus.OK, "User deleted");
	}

}
