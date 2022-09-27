package com.spring.security.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeControllerAdvice {

	@ResponseStatus(code=HttpStatus.BAD_REQUEST,value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> saveEmployeeExceptionHandler(MethodArgumentNotValidException ex){
		Map<String, String> errMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			
			errMap.put(error.getField(), error.getDefaultMessage());
		});
		return errMap;
	}
}
