package com.hoang.jiraclonebe.security;

/**
 * This class is used for default security variable constants.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
public class SecurityConstants {

    // Authorization Security configurations routes
    public static final String SIGN_UP_URL = "/api/users/**";
    public static final String H2_URL = "h2-console/**";

    // Token Security Constants
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer "; //need space for tokens
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30000_000; // 30 seconds
}
