package com.spring.security.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.security.constants.Constants;
import com.spring.security.entity.Address;
import com.spring.security.entity.Employee;
import com.spring.security.model.EmployeeModel;
import com.spring.security.model.ResponseModel;
import com.spring.security.repo.AddressRepo;
import com.spring.security.repo.EmployeeRepo;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepo employeeRepo;
	@MockBean
	private AddressRepo addressRepo;

	private EmployeeModel employeeModel;

	private Employee employee;

	private Address address;

	private ResponseModel res;

	@BeforeEach
	void setUp() {
		employeeModel = EmployeeModel.build("OMS001", "Rahul Kumar", "14/08/1992", "D-108 , Sector -2", null, "Noida",
				"U.P.", "201301");

		address = Address.build(0L, employeeModel.getAddress1(), employeeModel.getAddress2(), employeeModel.getCity(),
				employeeModel.getState(), employeeModel.getPincode());
		employee = Employee.build(0L, employeeModel.getEmpId(), employeeModel.getName(), employeeModel.getDob(),
				address);
		res = ResponseModel.build(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, employeeModel);

	}

	@Test
	@DisplayName("Employee's details should updated.")
	void testUpdateEmployee_WhenValidDetailsProvided_ThenReturnEmployeeData() {

//		Arrange

		when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
		when(addressRepo.save(any(Address.class))).thenReturn(address);
//		Act

		ResponseModel returnedResponse = employeeService.saveEmployee(employeeModel);

//		Assert

		Assertions.assertNotNull(returnedResponse, "Save method should return response model");
		Assertions.assertEquals(Constants.SUCCESS_CODE, returnedResponse.getStatus(),
				"Save method returned incorrect response model status code");
		Assertions.assertNotNull(returnedResponse.getEmployeeDetails(),
				"Save method returned incorrect Employee model details");
		Assertions.assertEquals(employeeModel.getEmpId(), returnedResponse.getEmployeeDetails().getEmpId(),
				"Saved employee's emp id seems to be incorrect");
		Assertions.assertEquals(employeeModel.getDob(), returnedResponse.getEmployeeDetails().getDob(),
				"Saved employee's DOB seems to be incorrect");
		Assertions.assertEquals(employeeModel.getAddress1(), returnedResponse.getEmployeeDetails().getAddress1(),
				"Saved employee's Address line 1 seems to be incorrect");
		Assertions.assertEquals(employeeModel.getAddress2(), returnedResponse.getEmployeeDetails().getAddress2(),
				"Saved employee's Address line 2 seems to be incorrect");
		Assertions.assertEquals(employeeModel.getPincode(), returnedResponse.getEmployeeDetails().getPincode(),
				"Saved employee's postal code seems to be incorrect");
	}

	@Test
	void testSaveEmployee_WhenValidDetailsProvided_ThenReturnEmployeeData() {

//		Arrange

		when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
		when(addressRepo.save(any(Address.class))).thenReturn(address);
//		Act

		ResponseModel returnedResponse = employeeService.saveEmployee(employeeModel);

//		Assert

		Assertions.assertNotNull(returnedResponse, "Save method should return response model");
		Assertions.assertEquals(Constants.SUCCESS_CODE, returnedResponse.getStatus(),
				"Save method returned incorrect response model status code");
		Assertions.assertNotNull(returnedResponse.getEmployeeDetails(),
				"Save method returned incorrect Employee model details");
		Assertions.assertEquals(employeeModel.getEmpId(), returnedResponse.getEmployeeDetails().getEmpId(),
				"Saved employee's emp id seems to be incorrect");
		Assertions.assertEquals(employeeModel.getDob(), returnedResponse.getEmployeeDetails().getDob(),
				"Saved employee's DOB seems to be incorrect");
		Assertions.assertEquals(employeeModel.getAddress1(), returnedResponse.getEmployeeDetails().getAddress1(),
				"Saved employee's Address line 1 seems to be incorrect");
		Assertions.assertEquals(employeeModel.getAddress2(), returnedResponse.getEmployeeDetails().getAddress2(),
				"Saved employee's Address line 2 seems to be incorrect");
		Assertions.assertEquals(employeeModel.getPincode(), returnedResponse.getEmployeeDetails().getPincode(),
				"Saved employee's postal code seems to be incorrect");
	}

}
