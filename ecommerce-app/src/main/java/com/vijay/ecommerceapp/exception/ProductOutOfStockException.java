package com.vijay.ecommerceapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductOutOfStockException extends RuntimeException {

	public ProductOutOfStockException(String message) {
		super(message);
	}
}
