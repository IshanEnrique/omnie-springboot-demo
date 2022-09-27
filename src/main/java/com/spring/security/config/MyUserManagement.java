package com.spring.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.SecurityCustomer;
import com.spring.security.entity.Customer;
import com.spring.security.repo.CustomerRepo;

@Service
public class MyUserManagement  implements UserDetailsService {

	@Autowired
	private CustomerRepo customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> customer = customerRepository.findByEmail(username);
		if (customer.size() == 0) {
			throw new UsernameNotFoundException("User details not found for the user : " + username);
		}
		return new SecurityCustomer(customer.get(0));
	}
	
	
}
