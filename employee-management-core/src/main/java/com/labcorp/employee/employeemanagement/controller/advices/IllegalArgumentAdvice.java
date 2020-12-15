package com.labcorp.employee.employeemanagement.controller.advices;

import com.labcorp.employee.employeemanagement.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IllegalArgumentAdvice {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> illegalArgumentHandler(IllegalArgumentException exception) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(exception.getMessage());
        responseDTO.setCode(400);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
