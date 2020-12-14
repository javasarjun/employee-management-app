package com.labcorp.employee.EmployeeManagement.services;

import java.util.List;
import java.util.UUID;

import com.labcorp.employee.EmployeeManagement.model.Employee;

public interface EmployeeService {
	
	public List<Employee>  getEmployees();
	public Employee setWorkDays(UUID id, int days);
	public Employee setVacationDays(UUID id, float days);

}
