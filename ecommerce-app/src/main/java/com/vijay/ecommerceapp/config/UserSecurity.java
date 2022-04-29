package com.vijay.ecommerceapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.vijay.ecommerceapp.repository.UserRepository;

@Component("userSecurity")
public class UserSecurity {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean hasUserId(Authentication authentication, Long userId) {
		Long userID = userRepository.findByEmail(authentication.getName()).getUserId();
		
		if(userID==userId)
			return true;
		return false;
	}

}
