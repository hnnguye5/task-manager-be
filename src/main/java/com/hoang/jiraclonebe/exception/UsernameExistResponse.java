package com.hoang.jiraclonebe.exception;

/**
 * The class handles UsernameExist Identifier object.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
public class UsernameExistResponse {

    private String username;

    public UsernameExistResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
