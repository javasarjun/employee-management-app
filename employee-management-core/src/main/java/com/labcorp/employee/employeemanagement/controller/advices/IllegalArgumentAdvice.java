package com.labcorp.employee.employeemanagement.controller.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IllegalArgumentAdvice {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentHandler(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
