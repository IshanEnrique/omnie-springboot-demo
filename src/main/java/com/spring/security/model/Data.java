package com.spring.security.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Data
@AllArgsConstructor(staticName = "build", access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class Data {

	public EmployeeModel employeeDetails;
	public CustomerModel customerDetails;
	public List<EmployeeModel> employeeList;

	public static Data create(Object arg) {
		return Data.build(arg instanceof EmployeeModel ? (EmployeeModel) arg : null,
				arg instanceof CustomerModel ? (CustomerModel) arg : null,
				arg instanceof List<?> ? (List<EmployeeModel>) arg : null);
	}
//	public Data(EmployeeModel employeeDetails) {
//		this.employeeDetails = employeeDetails;
//	}
//
//	public Data(List<EmployeeModel> employeeList) {
//		this.employeeList = employeeList;
//	}
//	public Data(CustomerModel customerDetails) {
//		this.customerDetails = customerDetails;
//	}

}
