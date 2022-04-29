package com.vijay.ecommerceapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.ecommerceapp.dto.model.UserDto;
import com.vijay.ecommerceapp.dto.model.UserResponse;
import com.vijay.ecommerceapp.service.UserService;
import com.vijay.ecommerceapp.util.Response;

@RestController
public class UserController {
	
	static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@PostMapping("/users")
	public Response<UserResponse> createUser(@Valid @RequestBody UserDto userDto) {
		log.info("CreateUser method Called");
		return service.createUser(userDto);
	}
	
	@GetMapping("/users/{userId}")
	public Response<UserResponse> findUser(@PathVariable Long userId) {
		log.info("FindUser method Called");
		return service.findUser(userId);
		
	}
	
	@PutMapping("/users/{userId}")
	public Response<UserResponse> UpdateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable Long userId) {
		log.info("UpdateUser method Called");
		return service.UpdateUser(userDto, userId);
		
	}
	
	@DeleteMapping("/users/{userId}")
	public Response<String> deleteUser(@PathVariable Long userId) {
		log.info("DeleteUser method Called");
		return service.deleteUser(userId);
	}

}
