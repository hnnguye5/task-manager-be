package com.hoang.jiraclonebe.exception;


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
