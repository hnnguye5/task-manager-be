package com.hoang.jiraclonebe.repository;

import com.hoang.jiraclonebe.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    User findByUsername(String username);
    // find user by db id
    User getById(Long id);

}
