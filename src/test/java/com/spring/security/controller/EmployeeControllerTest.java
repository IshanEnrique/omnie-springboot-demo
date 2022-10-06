package com.spring.security.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.security.constants.Constants;
import com.spring.security.entity.Address;
import com.spring.security.entity.Employee;
import com.spring.security.model.EmployeeModel;
import com.spring.security.model.ResponseModel;
import com.spring.security.service.EmployeeService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = EmployeeController.class, excludeAutoConfiguration = SecurityConfig.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

	@MockBean
	private EmployeeService empService;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private EmployeeModel employeeModel;
	@Mock
	private Employee employee;
	@Mock
	private Address address;

	private ResponseModel res;

	@BeforeAll
	private void setUp() {

		employeeModel = EmployeeModel.build("OMS001", "Rahul Kumar", "14/08/1992", "D-108 , Sector -2", null, "Noida",
				"U.P.", "201301");

		address = Address.build(0L, employeeModel.getAddress1(), employeeModel.getAddress2(), employeeModel.getCity(),
				employeeModel.getState(), employeeModel.getPincode());
		employee = Employee.build(0L, employeeModel.getEmpId(), employeeModel.getName(), employeeModel.getDob(),
				address);
		res = ResponseModel.build(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, employeeModel);
	}

//	@Disabled
	@DisplayName("New employee can be created")
	@Test
	@Order(1)
	void testSaveEmplyee_WhenValidDetailsProvided_ReturnSavedEmployeeDetails() throws Exception {

//		Arrange
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save-emp").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(employeeModel));

		Mockito.when(empService.saveEmployee(any(EmployeeModel.class))).thenReturn(res);
//		Act

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		String responseBodyAsString = mvcResult.getResponse().getContentAsString();
		ResponseModel responseModel = null;
		responseModel = new ObjectMapper().readValue(responseBodyAsString, ResponseModel.class);

//		Assert
		assertNotNull(responseModel.getEmployeeDetails(), "New employee created details can not be null.");
		assertEquals(employeeModel.getName(), responseModel.getEmployeeDetails().getName(),
				"The returned user's Name is most likely incorrect");
		assertNotNull(responseModel.getEmployeeDetails().getEmpId(), "The returned employye's code should be created.");

	}

	@Disabled
	@Test
	@DisplayName("When Name is not Valid")
	@Order(2)
	public void testSaveEmployee_WhenFirstNameIsNotValid_ThenReturn400StatusCode() throws Exception {
		
		
//		Arrange
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/save-emp")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(employeeModel));
		when(empService.saveEmployee(any(EmployeeModel.class))).thenReturn(res);
//		Act
		
		MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
//		String responseBody=mvcResult.getResponse().getContentAsString();
//		ResponseModel res=new ObjectMapper().readValue(responseBody, ResponseModel.class);
//		Assert
		
//		Assertions.assertThrows(IllegalArgumentException.class, ()->{},"Name field seems invalid.");
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus(),"Incorrect Http Status Code returned.");
	}

}
