package com.spring.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.model.EmployeeModel;
import com.spring.security.model.ResponseModel;
import com.spring.security.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	@PostMapping("save-emp")
	public ResponseEntity<ResponseModel> saveEmployee(@RequestBody @Valid EmployeeModel req){
		ResponseModel res=empService.saveEmployee(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("update-emp")
	public ResponseEntity<ResponseModel> updateEmployee(@RequestBody @Valid EmployeeModel req){
		ResponseModel res=empService.updateEmployee(req);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("get-emp/{empId}")
	public ResponseEntity<ResponseModel> getEmployee(@PathVariable String empId){
		ResponseModel res=empService.getEmployee(empId);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("get-emp")
	public ResponseEntity<ResponseModel> getAllEmployee() {
		ResponseModel res = empService.getAllEmployee();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	

	
}
