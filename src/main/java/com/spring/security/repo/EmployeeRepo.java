package com.spring.security.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

	public Optional<Employee> findByEmpId(String empId);

	public List<Employee> findAllByEmpId(String empId);

}
