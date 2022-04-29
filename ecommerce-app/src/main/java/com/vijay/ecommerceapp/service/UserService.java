package com.vijay.ecommerceapp.service;

import com.vijay.ecommerceapp.dto.model.UserDto;
import com.vijay.ecommerceapp.dto.model.UserResponse;
import com.vijay.ecommerceapp.util.Response;

public interface UserService {
	public Response<UserResponse> createUser(UserDto userDto);
	public Response<UserResponse> findUser(Long userId);
	public Response<UserResponse> UpdateUser(UserDto userDto, Long userId);
	public Response<String> deleteUser(Long userId);

}
