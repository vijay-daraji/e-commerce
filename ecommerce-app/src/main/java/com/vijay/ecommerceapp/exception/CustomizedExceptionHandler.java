package com.vijay.ecommerceapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vijay.ecommerceapp.util.ExceptionResponse;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CategoryAlreadyExistsException.class)
	public final ResponseEntity<Object> handleCategoryAlreadyExistsException
	(CategoryAlreadyExistsException ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		
		return new ResponseEntity(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<Object> handleUserAlreadyExistsException
	(UserAlreadyExistsException ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		
		return new ResponseEntity(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public final ResponseEntity<Object> handleProductAlreadyExistsException
	(ProductAlreadyExistsException ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		
		return new ResponseEntity(exceptionResponse,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<Object> handleProductNotFoundException
	(ProductNotFoundException ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductOutOfStockException.class)
	public final ResponseEntity<Object> handleProductOutOfStockException
	(ProductOutOfStockException ex, WebRequest request){
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders header, HttpStatus status, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				"Validation Faild", ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	
	

}
