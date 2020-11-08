package com.hoang.jiraclonebe.security;

import com.hoang.jiraclonebe.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.hoang.jiraclonebe.security.SecurityConstants.EXPIRATION_TIME;
import static com.hoang.jiraclonebe.security.SecurityConstants.SECRET;

/**
 * The class handles generating and validation of the JWT.
 * JWT.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {
        // exist within token
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiredDate = new Date(now.getTime() + EXPIRATION_TIME);
        String userId = Long.toString(user.getId());

        // claims to attach to user
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", Long.toString(user.getId()));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        return Jwts.builder().setSubject(userId).setClaims(claims).setIssuedAt(now).setExpiration(expiredDate).signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
