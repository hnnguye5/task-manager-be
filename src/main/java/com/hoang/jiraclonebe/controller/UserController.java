package com.hoang.jiraclonebe.controller;

import com.hoang.jiraclonebe.domain.User;
import com.hoang.jiraclonebe.payload.JWTLoginSuccessResponse;
import com.hoang.jiraclonebe.payload.LoginRequest;
import com.hoang.jiraclonebe.security.JwtTokenProvider;
import com.hoang.jiraclonebe.service.MapErrorValidation;
import com.hoang.jiraclonebe.service.UserService;
import com.hoang.jiraclonebe.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static com.hoang.jiraclonebe.security.SecurityConstants.TOKEN_PREFIX;


/**
 * This class is used for web request handler. It handles User
 * HTTP Request CRUD operations.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapErrorValidation mapErrorValidation;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * HTTP Request to authenticate user.
     *
     * @param  loginRequest      username and password credentials.
     * @param  result            the User errors when authenticating.
     * @return                   the User object mapping being able to authenticate.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapErrorValidation.errorMapValidation(result);
        if(errorMap != null)return errorMap;

        // security authentication username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    /**
     * HTTP Request to create a new user.
     *
     * @param  user       the User object.
     * @param  result     the User errors when creating a new user.
     * @return            the User object mapping being able create user.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        // Validate passwords rules
        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapErrorValidation.errorMapValidation(result);
        if(errorMap != null)return errorMap;

        User newUser = userService.saveUser(user);

        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
