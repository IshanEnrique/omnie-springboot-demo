package com.spring.security.controller;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.spring.security.constants.Constants;
import com.spring.security.entity.Address;
import com.spring.security.entity.Employee;
import com.spring.security.model.EmployeeModel;
import com.spring.security.model.ResponseModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "application-test.yml")
@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeControllerIntegrationTest {


	@Value("${spring.security.user.name}")
	private String securityUsername;
	@Value("${spring.security.user.password}")
	private String password;

	@Mock
	private EmployeeModel employeeModel;
	@Mock
	private Employee employee;
	@Mock
	private Address address;

	private ResponseModel res;

	private HttpHeaders headers;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@BeforeAll
	public  void setUp() {
//		testRestTemplate=new TestRestTemplate(securityUsername, password);
		employeeModel = EmployeeModel.build("OMS001", "Rahul Kumar", "14/08/1992", "D-108 , Sector -2", null, "Noida",
				"U.P.", "201301");

		address = Address.build(0L, employeeModel.getAddress1(), employeeModel.getAddress2(), employeeModel.getCity(),
				employeeModel.getState(), employeeModel.getPincode());
		employee = Employee.build(0L, employeeModel.getEmpId(), employeeModel.getName(), employeeModel.getDob(),
				address);
		res = ResponseModel.build(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, employeeModel);

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testSaveEmployee_WhenValidEmployeeDetailsProvided_ThenReturnEmployeeDetails() {

//		Arrange
		HttpEntity<EmployeeModel> httpEntity = new HttpEntity<EmployeeModel>(employeeModel, headers);

//		Act

		ResponseEntity<ResponseModel> createdEmployeeEntity = testRestTemplate.postForEntity("/save-emp", httpEntity,
				ResponseModel.class);
		
		res=createdEmployeeEntity.getBody();

//		Assert
		
		Assertions.assertEquals(HttpStatus.OK, createdEmployeeEntity.getStatusCode());
		Assertions.assertFalse(res.getEmployeeDetails().getEmpId().isEmpty(),"Newly created employee id seems to be incorrect");
		Assertions.assertEquals(employeeModel.getEmpId(), res.getEmployeeDetails().getEmpId(),()->"Employee code for the name "+employeeModel.getName()+" does not matched with created employee.");
		Assertions.assertEquals(employeeModel.getName(), res.getEmployeeDetails().getName(),()->"Employee Name for the code "+employeeModel.getName()+" does not matched with created employee.");
	}

}
