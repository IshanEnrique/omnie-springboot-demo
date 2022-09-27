package com.spring.security.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

	List<Customer> findByEmail(String email);
}
