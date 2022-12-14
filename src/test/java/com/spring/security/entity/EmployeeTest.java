package com.spring.security.entity;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.spring.security.model.EmployeeModel;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeTest {

	@Autowired
	private TestEntityManager entityManager;

	private EmployeeModel employeeModel;

	@Value("${server.port}")
	private int port;

	@BeforeEach
	public void setUp() {
		System.out.println("Server port :" + port);
		employeeModel = EmployeeModel.build("OMS01", "Rahul Kumar", "14/08/1992", "D-108 , Sector -2", null, "Noida",
				"U.P.", "201301");

	}

	@Order(1)
	@Test
	@DisplayName("Employee details should be saved")

	void testSaveEmployee_WhenValidDetailsProvided_ThenShouldReturnEmployeeDetails() {

//		Arrange
		Employee employee = employeeModel.employeeInstance();
		Address address = employee.getAddress();
//		Act

		Employee storedEmployee = entityManager.persist(employee);
		Address storedAddress = entityManager.persist(address);
		entityManager.flush();
		storedEmployee.setAddress(storedAddress);
//		Assert

		Assertions.assertTrue(storedEmployee.getId() > 0,
				"Employee enitiy not saving in DB , enitie's Id is not available");

		Assertions.assertEquals(employeeModel.getEmpId(), storedEmployee.getEmpId(),
				"Employee Id should be same as given for persistance.");
		Assertions.assertEquals(employeeModel.getName(), storedEmployee.getName(),
				"Employee Name should be same as given for persistance.");
		Assertions.assertEquals(employeeModel.getDob(), storedEmployee.getDob(),
				"Employee DOB should be same as given for persistance.");
		Assertions.assertEquals(employeeModel.getAddress1(), storedEmployee.getAddress().getAddress1(),
				"Employee Address should be same as given for persistance.");
		Assertions.assertEquals(employeeModel.getAddress1(), storedEmployee.getAddress().getAddress1(),
				"Employee Address1 should be same as given for persistance.");
	}

	@Order(2)
	@Test
	@DisplayName("Persistance Exception , When Name is too long.")
	public void testEmployee_WhenNameIsTooLong_ThenShouldThrowException() {

//		Arrange

		employeeModel.setName(
				"dsjflsjafsa9874834lsjdflsjd837498743jdslfjsad3849324dsjflsjafsa9874834lsjdflsjd837498743jdslfjsad3849324");
		employeeModel.setEmpId("OMS002");
		Employee employee = employeeModel.employeeInstance();
		Address address = employee.getAddress();

//		Act

//		Act && Assert

		Assertions.assertThrows(PersistenceException.class, () -> {

			Employee storedEmployee = entityManager.persist(employee);
			Address storedAddress = entityManager.persist(address);
			entityManager.flush();
		}, "Was expecting persistence exception to be thrown.");
	}

	@Order(3)
	@Test
	@DisplayName("Unique Constraint exception , when EmpId is same.")
	public void testEmployee_WhenEmpIdIsNotUnique_ThenShouldThrowException() {

//		Arrange

		employeeModel = EmployeeModel.build("OMS01", "Rahul Kumar", "14/08/1992", "D-108 , Sector -2", null, "Noida",
				"U.P.", "201301");
		Employee employee = employeeModel.employeeInstance();
		Address address = employee.getAddress();
		employeeModel = EmployeeModel.build("OMS01", "Sunil Kumar", "14/08/1992", "D-108 , Sector -2", null, "Noida",
				"U.P.", "201301");
		Employee employee1 = employeeModel.employeeInstance();
		Address address1 = employee1.getAddress();

//		Act

//		Act && Assert

		Assertions.assertThrows(PersistenceException.class, () -> {

			Employee storedEmployee = entityManager.persist(employee);
			Address storedAddress = entityManager.persist(address);
			entityManager.flush();

			entityManager.clear();
			Employee storedEmployee1 = entityManager.persist(employee1);
			Address storedAddress1 = entityManager.persist(address1);
			entityManager.flush();
			System.out.println(storedEmployee);
			System.out.println(storedAddress);
			System.out.println(storedEmployee1);
			System.out.println(storedAddress1);
		}, "Was expecting UniqueConstraintVoilation exception to be thrown.");
	}

}
