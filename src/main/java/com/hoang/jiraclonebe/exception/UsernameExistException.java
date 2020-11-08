package com.hoang.jiraclonebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class is used to create String messages.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameExistException extends RuntimeException{

    public UsernameExistException(String message) {
        super(message);
    }
}
