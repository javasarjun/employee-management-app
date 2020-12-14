package com.labcorp.employee.employeemanagement.model;

import com.labcorp.employee.employeemanagement.constants.EmployeeConstant;

import java.util.UUID;

public class HourlyEmployee extends Employee {

	public HourlyEmployee(String name, UUID id) {
		super(EmployeeConstant.MAX_VACATION_DAYS_IN_HOURLY, name, EmployeeType.HOURLY, id);
	}

}
