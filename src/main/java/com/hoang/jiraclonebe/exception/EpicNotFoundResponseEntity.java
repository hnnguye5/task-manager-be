package com.hoang.jiraclonebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class handles the HTTP Request for Epic not found.
 *
 * @author Hoang Nguyen
 * @version 1.0, 20 Oct 2020
 */
@RestController
@ControllerAdvice
public class EpicNotFoundResponseEntity {

    /**
     * Handles the response for duplicate Epic IDs.
     *
     * @param  ex    custom message.
     * @return       message that the Epic object is not found.
     */
    @ExceptionHandler
    public ResponseEntity<?> epicNotFoundResponseExceptionHandler(EpicNotFoundException ex) {

        EpicNotFoundExceptionResponse epicNotFoundExceptionResponse = new EpicNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity(epicNotFoundExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
