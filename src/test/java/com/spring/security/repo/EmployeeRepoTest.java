package com.spring.security.repo;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.spring.security.entity.Address;
import com.spring.security.entity.Employee;
import com.spring.security.model.EmployeeModel;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
class EmployeeRepoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepo employeeRepo;
	private EmployeeModel employeeModel;

	@BeforeEach
	void setUp() {
		employeeModel = EmployeeModel.build("OMS01", "Rahul Kumar", "14/08/1992", "D-108 , Sector -2", null, "Noida",
				"U.P.", "201301");
	}

	@Test
	void testFindByEmpId_WhenGivenCorrectEmpId_ThenShouldReturnEmployeeList() {

//		Arrange

		Employee employee = employeeModel.employeeInstance();
		Address address = employee.getAddress();
		entityManager.persist(employee);
		entityManager.persist(address);
		entityManager.flush();
//		Act

		Optional<Employee> storedEmployee = employeeRepo.findByEmpId(employee.getEmpId());
//		Assert

		Assertions.assertTrue(storedEmployee.isPresent(), "By stored employee id , Employee details should fetched.");
		Assertions.assertEquals(employee.getEmpId(), storedEmployee.get().getEmpId(),
				"The returned employee id does not matched with actual.");
		Assertions.assertNotNull(storedEmployee.get().getAddress(),
				"The returned employee should also contains address details.");
	}

}
