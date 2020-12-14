package com.labcorp.employee.employeemanagement.model;

import com.labcorp.employee.employeemanagement.constants.EmployeeConstant;

import java.util.UUID;

public class Manager extends SalariedEmployee {

	public Manager(String name, UUID id) {
		super(EmployeeConstant.MAX_VACATION_DAYS_IN_YEAR_MANAGER, name, EmployeeType.MANAGER, id);
	}



}
