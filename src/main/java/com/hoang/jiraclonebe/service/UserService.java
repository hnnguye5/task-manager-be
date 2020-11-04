package com.hoang.jiraclonebe.service;

import com.hoang.jiraclonebe.domain.User;
import com.hoang.jiraclonebe.exception.UsernameExistException;
import com.hoang.jiraclonebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // encode password
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());

            return userRepository.save(newUser);
        }
        catch(Exception e) {
            throw new UsernameExistException("Username: " + newUser + " already exist");
        }

    }
}
