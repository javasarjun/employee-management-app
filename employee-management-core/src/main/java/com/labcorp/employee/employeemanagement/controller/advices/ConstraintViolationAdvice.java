package com.labcorp.employee.employeemanagement.controller.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ConstraintViolationAdvice {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationFoundHandler(ConstraintViolationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
