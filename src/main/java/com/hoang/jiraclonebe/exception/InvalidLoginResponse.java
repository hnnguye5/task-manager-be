package com.hoang.jiraclonebe.exception;

/**
 * The class handles the HTTP Request when Username or Password is invalid.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
public class InvalidLoginResponse {

    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
