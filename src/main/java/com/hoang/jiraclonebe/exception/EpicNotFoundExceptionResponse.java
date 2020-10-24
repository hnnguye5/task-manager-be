package com.hoang.jiraclonebe.exception;

/**
 * The class handles EpicNotFound object.
 *
 * @author Hoang Nguyen
 * @version 1.0, 20 Oct 2020
 */
public class EpicNotFoundExceptionResponse {

    private String epicNotFound;

    public EpicNotFoundExceptionResponse(String epicNotFound) {
        this.epicNotFound = epicNotFound;
    }

    public String getEpicNotFound() {
        return epicNotFound;
    }

    public void setEpicNotFound(String epicNotFound) {
        this.epicNotFound = epicNotFound;
    }
}
