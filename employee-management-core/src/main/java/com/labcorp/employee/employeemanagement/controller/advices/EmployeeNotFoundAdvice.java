package com.labcorp.employee.employeemanagement.controller.advices;

import com.labcorp.employee.employeemanagement.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeNotFoundAdvice {
	
	   @ExceptionHandler(value = EmployeeNotFoundException.class)
	   public ResponseEntity<Object> employeeNotFoundHandler(EmployeeNotFoundException exception) {
	      return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	   }
}
