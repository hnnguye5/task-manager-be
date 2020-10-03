package com.hoang.jiraclonebe.service;

import com.hoang.jiraclonebe.domain.Epic;
import com.hoang.jiraclonebe.exception.EpicIdException;
import com.hoang.jiraclonebe.repository.EpicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The class handles the business logic for each method.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@Service
public class EpicService {

    @Autowired
    private EpicRepository epicRepository;

    /**
     * Creates or Update an Epic and returns as an object.
     *
     * @param  epic    the epic object
     * @return         the epic object being saved or updated.
     */
    public Epic saveOrUpdate(Epic epic) {

        // checks to see if Epic already exists
        try{
           epic.setEpicIdentifier(epic.getEpicIdentifier().toUpperCase());
           return epicRepository.save(epic);
       }
       catch(Exception e) {
           throw new EpicIdException("Epic ID " + epic.getEpicIdentifier().toUpperCase() + " already exist");
       }
    }
}
