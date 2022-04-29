package com.vijay.ecommerceapp.util;

public class ExceptionResponse {
	
	private String error_message;
	private String details;
	
	public ExceptionResponse(String error_message, String details) {
		super();
		this.error_message = error_message;
		this.details = details;
	}
	public String getError_message() {
		return error_message;
	}
	public String getDetails() {
		return details;
	}

}
