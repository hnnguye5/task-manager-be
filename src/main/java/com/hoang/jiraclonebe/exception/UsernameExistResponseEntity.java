package com.hoang.jiraclonebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class UsernameExistResponseEntity extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> usernameExistResponseEntity(UsernameExistException existException) {

        UsernameExistResponse usernameExistResponse = new UsernameExistResponse(existException.getMessage());

        return new ResponseEntity(usernameExistResponse, HttpStatus.BAD_REQUEST);
    }
}
