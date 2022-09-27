package com.spring.security.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.Address;

@Repository
public interface AddressRepo extends CrudRepository<Address, Long> {

	
}
