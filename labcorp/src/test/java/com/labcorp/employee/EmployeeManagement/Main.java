package com.labcorp.employee.EmployeeManagement;

import com.labcorp.employee.EmployeeManagement.model.Employee;
import com.labcorp.employee.EmployeeManagement.model.HourlyEmployee;
import com.labcorp.employee.EmployeeManagement.model.Manager;
import com.labcorp.employee.EmployeeManagement.model.SalariedEmployee;

import java.util.UUID;

public class Main {
	
	public static void main(String[] args) throws IllegalAccessException {
		Employee hourlyEmployee = new HourlyEmployee("arjun vaid", UUID.randomUUID());
		Employee salaryEmployee = new SalariedEmployee("tanvi vaid", UUID.randomUUID());
		Employee managerSalariedEmployee = new Manager("travis vaid", UUID.randomUUID());
		hourlyEmployee.work(10);
		salaryEmployee.work(10);
		managerSalariedEmployee.work(10);
		System.out.println(hourlyEmployee.getVacationDaysAccumulated());
		System.out.println(salaryEmployee.getVacationDaysAccumulated());
		System.out.println(managerSalariedEmployee.getVacationDaysAccumulated());
		hourlyEmployee.work(50);
		salaryEmployee.work(50);
		managerSalariedEmployee.work(50);
		System.out.println(hourlyEmployee.getVacationDaysAccumulated());
		System.out.println(salaryEmployee.getVacationDaysAccumulated());
		System.out.println(managerSalariedEmployee.getVacationDaysAccumulated());
		hourlyEmployee.work(200);
		salaryEmployee.work(200);
		managerSalariedEmployee.work(200);
		System.out.println(hourlyEmployee.getVacationDaysAccumulated());
		System.out.println(salaryEmployee.getVacationDaysAccumulated());
		System.out.println(managerSalariedEmployee.getVacationDaysAccumulated());
		hourlyEmployee.work(200);
		salaryEmployee.work(10);
		managerSalariedEmployee.work(10);
		System.out.println(hourlyEmployee.getVacationDaysAccumulated());
		System.out.println(salaryEmployee.getVacationDaysAccumulated());
		System.out.println(managerSalariedEmployee.getVacationDaysAccumulated());
		hourlyEmployee.takeVacation(1);
		salaryEmployee.takeVacation(1);
		managerSalariedEmployee.takeVacation(2);
		System.out.println(hourlyEmployee.getVacationDaysAccumulated());
		System.out.println(salaryEmployee.getVacationDaysAccumulated());
		System.out.println(managerSalariedEmployee.getVacationDaysAccumulated());
		hourlyEmployee.takeVacation((float) 1.5);
		salaryEmployee.takeVacation((float) 1.5);
		managerSalariedEmployee.takeVacation((float) 2.9);
		
		
		
	}
}
