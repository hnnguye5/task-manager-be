package com.hoang.jiraclonebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The class handles the HTTP Request for the duplicate Epic IDs.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@RestController
@ControllerAdvice
public class EpicIdResponseEntity extends ResponseEntityExceptionHandler {

    /**
     * Handles the response for duplicate Epic IDs.
     *
     * @param  exception    custom message.
     * @return              message that the object has duplicate Epic IDs.
     */
    @ExceptionHandler
    public final ResponseEntity<Object> epicIdExceptionHandler(EpicIdException exception) {

        EpicIdExceptionResponse exceptionResponse = new EpicIdExceptionResponse(exception.getMessage());

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
