package com.hoang.jiraclonebe.repository;

import com.hoang.jiraclonebe.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is the JPA Repository. It has CRUD method calls
 * and also has the ability to create custom queries to find
 * any particular data.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    User findByUsername(String username);
    // find user by db id
    User getById(Long id);

}
