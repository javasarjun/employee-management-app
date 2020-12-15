package com.labcorp.employee.employeemanagement.controller.advices;

import com.labcorp.employee.employeemanagement.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ConstraintViolationAdvice {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ResponseDTO> constraintViolationFoundHandler(ConstraintViolationException exception) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(exception.getMessage());
        responseDTO.setCode(400);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
