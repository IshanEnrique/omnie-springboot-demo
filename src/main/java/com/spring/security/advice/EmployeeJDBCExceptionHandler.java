package com.spring.security.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeJDBCExceptionHandler {

	@ExceptionHandler(DataAccessException.class)
	public Map<String, String> jdbcExceptionHandler(DataAccessException ex) {
		Map<String, String> errMap = new HashMap<>();
		errMap.put("status", "ERR01");
		errMap.put("message", 
				ex instanceof DataIntegrityViolationException
				? "Create or Update operation failed , due to already exists in the system.":ex.getMostSpecificCause().getMessage()
				);
		return errMap;
	}
}
