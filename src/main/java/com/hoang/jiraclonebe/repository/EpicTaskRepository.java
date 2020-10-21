package com.hoang.jiraclonebe.repository;

import com.hoang.jiraclonebe.domain.EpicTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is the JPA Repository. It has CRUD method calls
 * and also has the ability to create custom queries to find
 * any particular data.
 *
 * @author Hoang Nguyen
 * @version 1.0, 17 Oct 2020
 */
@Repository
public interface EpicTaskRepository extends CrudRepository<EpicTask, Long> {

    List<EpicTask> findByEpicIdentifierOrderByPriority(String epicIdentifier);

    EpicTask findByEpicSequence(String epicSequence);
}
