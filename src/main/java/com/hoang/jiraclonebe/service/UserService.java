package com.hoang.jiraclonebe.service;

import com.hoang.jiraclonebe.domain.User;
import com.hoang.jiraclonebe.exception.UsernameExistException;
import com.hoang.jiraclonebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The class handles the business logic for the User object with each CRUD method.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // encode password
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Creates a new User and returns as an object.
     *
     * @param  newUser      the new user being created.
     *
     * @return              the User object being saved.
     */
    public User saveUser(User newUser) {
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        }
        catch(Exception e) {
            throw new UsernameExistException("Username: " + newUser + " already exist");
        }

    }
}
