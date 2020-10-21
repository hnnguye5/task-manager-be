package com.hoang.jiraclonebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class EpicNotFoundResponseEntity {

    @ExceptionHandler
    public ResponseEntity<?> epicNotFoundResponseExceptionHandler(EpicNotFoundException ex) {

        EpicNotFoundExceptionResponse epicNotFoundExceptionResponse = new EpicNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity(epicNotFoundExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
