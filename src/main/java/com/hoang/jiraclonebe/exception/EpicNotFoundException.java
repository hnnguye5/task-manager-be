package com.hoang.jiraclonebe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EpicNotFoundException extends RuntimeException{

    public EpicNotFoundException(String message) {
        super(message);
    }
}
