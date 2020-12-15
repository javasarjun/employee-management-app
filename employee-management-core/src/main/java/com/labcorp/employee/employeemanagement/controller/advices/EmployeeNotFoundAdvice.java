package com.labcorp.employee.employeemanagement.controller.advices;

import com.labcorp.employee.employeemanagement.dto.ResponseDTO;
import com.labcorp.employee.employeemanagement.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeNotFoundAdvice {
	
	   @ExceptionHandler(value = EmployeeNotFoundException.class)
	   public ResponseEntity<ResponseDTO> employeeNotFoundHandler(EmployeeNotFoundException exception) {
		   ResponseDTO responseDTO = new ResponseDTO();
		   responseDTO.setMessage("Employee not found");
		   responseDTO.setCode(400);
	      return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
	   }
}
