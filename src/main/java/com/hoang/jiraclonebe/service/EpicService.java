package com.hoang.jiraclonebe.service;

import com.hoang.jiraclonebe.domain.Backlog;
import com.hoang.jiraclonebe.domain.Epic;
import com.hoang.jiraclonebe.exception.EpicIdException;
import com.hoang.jiraclonebe.repository.BacklogRepository;
import com.hoang.jiraclonebe.repository.EpicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The class handles the business logic for the Epic object with each CRUD method.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@Service
public class EpicService {

    @Autowired
    private EpicRepository epicRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    /**
     * Creates or Update an Epic and returns as an object.
     *
     * @param  epic    the Epic object.
     * @return         the Epic object being saved or updated.
     */
    public Epic saveOrUpdate(Epic epic) {

        // checks to see if Epic already exists
        try{
           epic.setEpicIdentifier(epic.getEpicIdentifier().toUpperCase());

           // create new backlog if its a new Epic being created
           if(epic.getId() == null) {
               Backlog backlog = new Backlog();
               epic.setBacklog(backlog);
               backlog.setEpic(epic);
               backlog.setEpicIdentifier(epic.getEpicIdentifier().toUpperCase());
           }

           // when update, make sure backlog still has relations to epic
           if(epic.getId() != null) {
                epic.setBacklog(backlogRepository.findByEpicIdentifier(epic.getEpicIdentifier().toUpperCase()));
           }
           return epicRepository.save(epic);
       }
       catch(Exception e) {
           throw new EpicIdException("Epic ID " + epic.getEpicIdentifier().toUpperCase() + " already exist");
       }
    }

    /**
     * Finds all Epic objects that exist.
     *
     * @return         list of all Epics.
     */
    public Iterable<Epic> findAllEpics() {

        return epicRepository.findAll();
    }

    /**
     * Finds an Epic object by its identifier.
     *
     * @param  epicIdentifier    the Epic identifier
     * @return                   the Epic object.
     */
    public Epic findEpicByIdentifier(String epicIdentifier) {

        Epic epic = epicRepository.findByEpicIdentifier(epicIdentifier.toUpperCase());

        if(epic == null) {
            throw new EpicIdException("Epic ID " + epicIdentifier.toUpperCase() + " does not exist");
        }

        return epic;
    }

    /**
     * Delete an Epic object by its identifier if it exists.
     *
     * @param  epicIdentifier    the Epic identifier
     * @return                   the existing Epic identifier.
     */
    public void deleteEpicByIdentifier(String epicIdentifier) {

        Epic epic = epicRepository.findByEpicIdentifier(epicIdentifier.toUpperCase());

        if(epic == null) {
            throw new EpicIdException("Epic ID " + epicIdentifier.toUpperCase() + " does not exist");
        }

        epicRepository.delete(epic);
    }
}