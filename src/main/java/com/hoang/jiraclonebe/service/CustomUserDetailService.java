package com.hoang.jiraclonebe.service;

import com.hoang.jiraclonebe.domain.User;
import com.hoang.jiraclonebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The class handles custom User Details. This allows user verification
 * that is used by JWT.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Finds user by a username
     *
     * @param  username    the username of the User.
     * @return             the username of the User.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User is not found");
        }

        return user;
    }

    /**
     * Finds user by a username
     *
     * @param  id    the id(database) of the User.
     * @return       the id(database) of the User.
     */
    @Transactional
    public User loadUserById(Long id) {
        User user = userRepository.getById(id);
  
        if(user == null) {
            throw new UsernameNotFoundException("User is not found");
        }

        return user;
    }
}
