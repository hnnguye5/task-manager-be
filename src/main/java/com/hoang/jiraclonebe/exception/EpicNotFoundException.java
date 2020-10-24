package com.hoang.jiraclonebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class is used to create String messages.
 *
 * @author Hoang Nguyen
 * @version 1.0, 20 Oct 2020
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EpicNotFoundException extends RuntimeException{

    /**
     * Creates custom message when Epic is not found.
     *
     * @param  message  custom message.
     * @return          custom message.
     */
    public EpicNotFoundException(String message) {
        super(message);
    }
}
