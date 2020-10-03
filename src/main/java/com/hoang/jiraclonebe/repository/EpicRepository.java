package com.hoang.jiraclonebe.repository;

import com.hoang.jiraclonebe.domain.Epic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is the JPA Repository. It has CRUD method calls
 * and also has the ability to create custom queries to find
 * any particular data.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@Repository
public interface EpicRepository extends CrudRepository<Epic, Long> {

    Epic findByEpicIdentifier(String epicIdentifier);
    Iterable<Epic> findAll();
}
