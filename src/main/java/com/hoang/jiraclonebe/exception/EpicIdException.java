package com.hoang.jiraclonebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class is used to create String messages.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EpicIdException extends RuntimeException{

    /**
     * Creates custom message for duplicate Epic IDs.
     *
     * @param  message  custom message
     * @return          custom message
     */
    public EpicIdException(String message) {
        super(message);
    }
}
