package com.hoang.jiraclonebe.exception;

/**
 * The class handles Epic Identifier object.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */

public class EpicIdExceptionResponse {

    private String epicIdentifier;

    public EpicIdExceptionResponse(String epicIdentifier) {
        this.epicIdentifier = epicIdentifier;
    }

    public String getEpicIdentifier() {
        return epicIdentifier;
    }

    public void setEpicIdentifier(String epicIdentifier) {
        this.epicIdentifier = epicIdentifier;
    }
}
