package com.hoang.jiraclonebe.payload;

/**
 * The class handles the request when logging in with a user.
 * JWT.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Must have a username")
    private String username;
    @NotBlank(message = "Must have a password")
    private String password;

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
