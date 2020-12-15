package com.labcorp.employee.employeemanagement.model;
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

	public void setVacationDaysAccumulated(float vacationDaysAccumulated) {
		this.vacationDaysAccumulated = vacationDaysAccumulated;
	}

	public float getVacationDaysAccumulated() {
		return vacationDaysAccumulated;
	}

	public int getDaysWorked() {
		return daysWorked;
	}

	public void setDaysWorked(int daysWorked) {
		this.daysWorked = daysWorked;
	}

	public float getMaxVacationDaysInWorkYear() {
		return maxVacationDaysInWorkYear;
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
