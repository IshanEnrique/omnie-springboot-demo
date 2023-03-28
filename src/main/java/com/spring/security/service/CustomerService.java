package com.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.security.constants.Constants;
import com.spring.security.entity.Customer;
import com.spring.security.model.CustomerModel;
import com.spring.security.model.Data;
import com.spring.security.model.ResponseModel;
import com.spring.security.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Transactional
	public ResponseModel saveCustomer(CustomerModel req) {
		Customer reqEntity = req.customerInstance();
		Customer savedEntity = customerRepo.save(reqEntity);
		if (null != savedEntity)
		{
			Data data = Data.create(CustomerModel.getEmployeeModelInstance(savedEntity));
			return ResponseModel.build(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG,
					data);
		}
		else {
			return ResponseModel.build(Constants.FAILURE_CODE, Constants.FAILURE_MSG,
					null);
		}
	}

}
