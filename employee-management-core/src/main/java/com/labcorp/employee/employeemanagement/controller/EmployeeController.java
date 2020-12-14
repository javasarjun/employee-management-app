package com.labcorp.employee.employeemanagement.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.labcorp.employee.employeemanagement.services.EmployeeService;
import com.labcorp.employee.employeemanagement.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class EmployeeController {
	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeController.class);

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody  List<Employee>  getEmployees() {
		return employeeService.getEmployees();
	}
	
	@PutMapping(value = "/employee/{id}/days-worked/{days}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Employee setWorkDays(@PathVariable UUID id,
											  @PathVariable
							  @Min(value = 0, message = "must be greater than or equal to 0")
							  @Max(value = 260, message = "must be less than or equal to 260") int days)  {
		return employeeService.setWorkDays(id, days);
	}
	
	@PutMapping(value = "/employee/{id}/take-vacation/{days}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Employee setVacationDays(@PathVariable UUID id,
								  @PathVariable @Min(value = 0, message = "must be greater than or equal to 0")
										  float days) {
		return employeeService.setVacationDays(id, days);
	}

}
