package com.spring.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.model.CustomerModel;
import com.spring.security.model.ResponseModel;
import com.spring.security.service.CustomerService;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
@RequestMapping(path = { "auth-gen/" })
public class CustomerController {

	@Autowired
	private CustomerService empService;

	@PostMapping("save-auth")
	public ResponseEntity<ResponseModel> saveEmployee(@RequestBody @Valid CustomerModel req) {
		ResponseModel res = empService.saveCustomer(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	

	

	
}
