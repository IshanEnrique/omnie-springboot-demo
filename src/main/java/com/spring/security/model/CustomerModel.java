package com.spring.security.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.security.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CustomerModel {

	@NotBlank(message = "Username/Email can't be blank")
	@NotNull(message = "Username/Email can't be null")
	private String email;
	@NotBlank(message = "Password can't be blank")
	@NotNull(message = "Password can't be null")
	private String pwd;
	@NotBlank(message = "Role can't be blank")
	@NotNull(message = "Role can't be null")
	private String role;

	public Customer customerInstance() {
		Customer entity = Customer.build(null, email, pwd, role);
		return entity;
	}
	@JsonIgnoreProperties
	public static CustomerModel getEmployeeModelInstance(Customer customer) {
		if (null == customer)
			return null;
		return CustomerModel.build(customer.getEmail(), customer.getPwd(), customer.getRole());
	}

}
