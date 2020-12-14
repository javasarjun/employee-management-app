package com.labcorp.employee.employeemanagement.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.labcorp.employee.employeemanagement.dao.EmployeeDAO;
import com.labcorp.employee.employeemanagement.exception.EmployeeNotFoundException;

import com.labcorp.employee.employeemanagement.model.Employee;
import com.labcorp.employee.employeemanagement.services.EmployeeService;
import com.labcorp.employee.employeemanagement.utils.VacationDaysUtility;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private VacationDaysUtility vacationDaysUtility;

	private EmployeeDAO employeeDAO;

	public EmployeeServiceImpl (EmployeeDAO employeeDAO, VacationDaysUtility vacationDaysUtility) {
		this.employeeDAO = employeeDAO;
		this.vacationDaysUtility = vacationDaysUtility;
	}

	
	@Override
	public List<Employee> getEmployees() {
		Optional<List<Employee>> optionalEmployees = employeeDAO.getAllEmployees();
		return optionalEmployees.isPresent() ? optionalEmployees.get() : Collections.EMPTY_LIST;
	}

	@Override
	public Employee setWorkDays(UUID id, int days) {
		Optional<Employee> optionalEmployee = employeeDAO.getEmployeeById(id);
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			vacationDaysUtility.work(days, employee);
			employeeDAO.save(employee);
			return employee;
		} else {
			throw new EmployeeNotFoundException();
		}
	}

	@Override
	public Employee setVacationDays(UUID id, float days) {
		Optional<Employee> optionalEmployee = employeeDAO.getEmployeeById(id);
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			vacationDaysUtility.takeVacation(days, employee);
			employeeDAO.save(employee);
			return employee;
		} else {
			throw new EmployeeNotFoundException();
		}
	}

}
