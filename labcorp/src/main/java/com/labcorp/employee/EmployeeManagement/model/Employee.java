package com.labcorp.employee.EmployeeManagement.model;

import com.labcorp.employee.EmployeeManagement.constants.EmployeeConstant;

import java.util.UUID;

public  class Employee {
	private UUID id;
	private float vacationDaysAccumulated;
	private String name;
	private int daysWorked;
	private float maxVacationDaysInWorkYear;
	private EmployeeType type;
	
	
	public Employee(float maxVacationDaysInWorkYear, String name, EmployeeType type, UUID id) {
		super();
		this.maxVacationDaysInWorkYear = maxVacationDaysInWorkYear;
		this.name = name;
		this.vacationDaysAccumulated = 0.0f;
		this.type = type;
		this.id = id;
	}

	public void work(int daysWorked) {
		if (daysWorked < 0)
			throw new IllegalArgumentException("Days worked should be a positive number");
		if (this.daysWorked + daysWorked <= EmployeeConstant.DAYS_IN_WORK_YEAR) {
			float vacationDayGenerated =  ((daysWorked / (float) EmployeeConstant.DAYS_IN_WORK_YEAR) * maxVacationDaysInWorkYear);
			vacationDaysAccumulated += vacationDayGenerated;
			this.daysWorked += daysWorked;
			if (vacationDaysAccumulated > maxVacationDaysInWorkYear)
				vacationDaysAccumulated = maxVacationDaysInWorkYear;
		} else {
			throw new IllegalArgumentException("Cannot work for more than " + EmployeeConstant.DAYS_IN_WORK_YEAR + " days in a work year");
		}
		
	}
	
	public void takeVacation(float vacationDays) {
		if (vacationDays < 0)
			throw new IllegalArgumentException("Vacation days should be a positive number");

		if (vacationDays <= this.vacationDaysAccumulated) {
			this.vacationDaysAccumulated -= vacationDays;
		} else {
			throw new IllegalArgumentException("Cannot take more vacation than available vacation days " + this.vacationDaysAccumulated);
		}
		
	}


	public float getVacationDaysAccumulated() {
		return vacationDaysAccumulated;
	}

	public int getDaysWorked() {
		return daysWorked;
	}

	public EmployeeType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}
}
