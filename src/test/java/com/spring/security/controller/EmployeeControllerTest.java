package com.spring.security.controller;

import static org.mockito.Mockito.ignoreStubs;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.security.entity.Address;
import com.spring.security.entity.Employee;
import com.spring.security.model.EmployeeModel;

@AutoConfigureMockMvc
@ContextConfiguration(classes =EmployeeController.class )
@WebMvcTest
@TestInstance(Lifecycle.PER_CLASS)
class EmployeeControllerTest {

	//private WebApplicationContext context;
	@Autowired
	private MockMvc mockMvc;
	

	@Mock
	private EmployeeModel employeeModel;
	@Mock
	private Employee employee;
	@Mock
	private Address address;


	@BeforeAll
	private void setUp() {

		/*context = Mockito.mock(WebApplicationContext.class);
		employeeControllerResource = Mockito.mock(EmployeeController.class);
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)

				.apply(springSecurity()).
				build();*/
//		employeeModel = EmployeeModel.build("OMS001", "Rahul Kumar", "14/08/1992", "D-108 , Sector -2", null, "Noida",
//				"U.P.", "201301");
		employeeModel = Mockito.mock(EmployeeModel.class);
		employee = Mockito.mock(Employee.class);
		address = Mockito.mock(Address.class);
	}

	@Test
	void test() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/save-emp", ignoreStubs(employeeModel))

		).andExpect(MockMvcResultMatchers.status().isOk())

		;
	}

}
