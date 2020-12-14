package com.labcorp.employee.EmployeeManagement.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.labcorp.employee.EmployeeManagement.exception.EmployeeNotFoundException;

import com.labcorp.employee.EmployeeManagement.dao.EmployeeDAO;
import com.labcorp.employee.EmployeeManagement.model.Employee;
import com.labcorp.employee.EmployeeManagement.services.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDao;

	public EmployeeServiceImpl (EmployeeDAO employeeDAO) {
		this.employeeDao = employeeDAO;
	}

	
	@Override
	public List<Employee> getEmployees() {
		Optional<List<Employee>> optionalEmployees = employeeDao.getAllEmployees();
		return optionalEmployees.isPresent() ? optionalEmployees.get() : Collections.EMPTY_LIST;
	}

	@Override
	public Employee setWorkDays(UUID id, int days) {
		Optional<Employee> optionalEmployee = employeeDao.getEmployeeById(id);
		if(optionalEmployee.isPresent()) {
			optionalEmployee.get().work(days);
		} else {
			throw new EmployeeNotFoundException();
		}
		return optionalEmployee.get();
	}

	@Override
	public Employee setVacationDays(UUID id, float days) {
		Optional<Employee> optionalEmployee = employeeDao.getEmployeeById(id);
		if(optionalEmployee.isPresent()) {
			optionalEmployee.get().takeVacation(days);
		} else {
			throw new EmployeeNotFoundException();
		}
		return optionalEmployee.get();
	}

}
