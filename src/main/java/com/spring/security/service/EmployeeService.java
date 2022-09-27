package com.spring.security.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.security.constants.Constants;
import com.spring.security.entity.Address;
import com.spring.security.entity.Employee;
import com.spring.security.model.EmployeeModel;
import com.spring.security.model.ResponseModel;
import com.spring.security.repo.AddressRepo;
import com.spring.security.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;
	@Autowired
	private AddressRepo addressRepo;

	@Transactional
	public ResponseModel saveEmployee(EmployeeModel req) {
		Employee reqEntity = req.getEmployeeInstance();
		Employee savedEntity = empRepo.save(reqEntity);
		Address savedAddressEntity=addressRepo.save(reqEntity.getAddress());
		if (null != savedEntity && null != savedAddressEntity)
			return ResponseModel.build(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG,
					EmployeeModel.getEmployeeModelInstance(savedEntity));
		else {
			return ResponseModel.build(Constants.FAILURE_CODE, Constants.FAILURE_MSG,
					null);
		}
	}

	public ResponseModel updateEmployee(EmployeeModel req) {
		Employee reqEntity = req.getEmployeeInstance();
		Optional<Employee> emOptional = empRepo.findByEmpId(reqEntity.getEmpId());
		if (emOptional.isPresent()) {
			reqEntity.setId(emOptional.get().getId());
			reqEntity.getAddress().setId(emOptional.get().getAddress().getId());
			Employee savedEntity = empRepo.save(reqEntity);
			Address savedAddressEntity=addressRepo.save(reqEntity.getAddress());
			savedEntity.setAddress(savedAddressEntity);
			return ResponseModel.build(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG,
					EmployeeModel.getEmployeeModelInstance(savedEntity));
		}

		else {
			return ResponseModel.build(Constants.FAILURE_CODE, Constants.FAILURE_MSG,
					null);
		}
	}

	public ResponseModel getEmployee(String empId) {

		Optional<Employee> emOptional = empRepo.findByEmpId(empId);
		if (emOptional.isPresent()) {
			Employee entity = emOptional.get();
			return ResponseModel.build(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG,
					EmployeeModel.getEmployeeModelInstance(entity));
		}

		else {
			return ResponseModel.build(Constants.FAILURE_CODE, Constants.FAILURE_MSG,
					null);
		}
	
	}

}
