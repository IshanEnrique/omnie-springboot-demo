package com.spring.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.security.entity.Address;
import com.spring.security.entity.Employee;

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
public class EmployeeModel {

	@NotNull(message = "Employee Id can't be null or blank")
	private String empId;
	@NotBlank(message = "Employee Name can't be blank")
	@NotNull(message = "Employee name can't be null")
	private String name;
	@Pattern(regexp = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$", message = "DOB should be correct.")
	private String dob;

	@NotNull(message = "Address 1 can't be null or blank")
	private String address1;
	private String address2;
	@NotNull(message = "City can't be null or blank")
	private String city;
	@NotNull(message = "State can't be null or blank")
	private String state;
	@Pattern(regexp = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$", message = "PIN Code should be correct.")
	private String pincode;

	public Employee employeeInstance() {
		Address address = Address.build(null, address1, address2, city, state, pincode);
		Employee entity = Employee.build(null, empId, name, dob, address);
		return entity;
	}
	@JsonIgnoreProperties
	public static EmployeeModel getEmployeeModelInstance(Employee emp) {
		if (null == emp || null == emp.getAddress())
			return null;
		Address address = emp.getAddress();
		return EmployeeModel.build(emp.getEmpId(), emp.getName(), emp.getDob(), address.getAddress1(),
				address.getAddress2(), address.getCity(), address.getState(), address.getPincode());
	}

	@JsonIgnoreProperties
	public static List<EmployeeModel> getEmployeeModelIListnstance(List<Employee> empList) {
		if (null == empList || empList.isEmpty())
			return null;
		List<EmployeeModel> empModelList = new ArrayList<>();
		empList.forEach(emp -> {

			Address address = emp.getAddress();
			empModelList.add(EmployeeModel.build(emp.getEmpId(), emp.getName(), emp.getDob(), address.getAddress1(),
					address.getAddress2(), address.getCity(), address.getState(), address.getPincode()));
		});
		return empModelList;
	}

}
