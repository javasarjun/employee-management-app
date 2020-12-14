package com.labcorp.employee.EmployeeManagement.model;

import com.labcorp.employee.EmployeeManagement.constants.EmployeeConstant;

import java.util.UUID;

public class SalariedEmployee extends Employee {

	public SalariedEmployee(String name, UUID id) {
		super(EmployeeConstant.MAX_VACATION_DAYS_IN_SALARIED, name, EmployeeType.SALARIED, id);
	}

	public SalariedEmployee(float maxVacationDaysInWorkYear, String name, EmployeeType type, UUID id) {
		super(maxVacationDaysInWorkYear, name, type, id);
	}
	
}
