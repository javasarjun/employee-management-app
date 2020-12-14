package com.labcorp.employee.employeemanagement.services;

import java.util.List;
import java.util.UUID;

import com.labcorp.employee.employeemanagement.model.Employee;

public interface EmployeeService {
	
	 List<Employee>  getEmployees();
	 Employee setWorkDays(UUID id, int days);
	 Employee setVacationDays(UUID id, float days);

}
