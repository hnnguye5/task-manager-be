package com.hoang.jiraclonebe.service;

import com.hoang.jiraclonebe.domain.Backlog;
import com.hoang.jiraclonebe.domain.Epic;
import com.hoang.jiraclonebe.domain.EpicTask;
import com.hoang.jiraclonebe.exception.EpicNotFoundException;
import com.hoang.jiraclonebe.repository.BacklogRepository;
import com.hoang.jiraclonebe.repository.EpicRepository;
import com.hoang.jiraclonebe.repository.EpicTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The class handles the business logic for the EpicTask object with each CRUD method.
 *
 * @author Hoang Nguyen
 * @version 1.0, 20 Oct 2020
 */
@Service
public class EpicTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private EpicTaskRepository epicTaskRepository;

    @Autowired
    private EpicRepository epicRepository;

    /**
     * Creates or Update an EpicTask and returns as an object.
     *
     * @param  epicIdentifier    the Epic Identifier.
     * @param  epicTask          the EpicTask object.
     * @return                   the Epic object being saved or updated.
     */
    public EpicTask addEpicTask(String epicIdentifier, EpicTask epicTask) {

        try{
            // establish relations with Backlog and Epic Task
            Backlog backlog = backlogRepository.findByEpicIdentifier((epicIdentifier.toUpperCase()));
            epicTask.setBacklog(backlog);

            // allows EpicTask to have its own IDs for each task using Backlog EpicSequence
            Integer backlogSequence = backlog.getEpicSequence();
            backlogSequence++;
            backlog.setEpicSequence(backlogSequence);
            epicTask.setEpicSequence(epicIdentifier + "-" + backlogSequence);
            epicTask.setEpicIdentifier(epicIdentifier);


            if(epicTask.getPriority() == null) {
                epicTask.setPriority(3);
            }

            if(epicTask.getStatus() == "" || epicTask.getStatus() ==null) {
                epicTask.setStatus("TO_DO");
            }

            return epicTaskRepository.save(epicTask);
        }
        catch(Exception e) {
            throw new EpicNotFoundException("Epic ID: " + epicIdentifier.toUpperCase() + " is not found");
        }

    }

    /**
     * Finds all EpicTask objects that exist.
     *
     * @param  epicIdentifier    the Epic Identifier.
     * @return                   list of all EpicTask.
     */
    public Iterable<EpicTask> findBacklogByIdentifier(String epicIdentifier) {

        Epic epic = epicRepository.findByEpicIdentifier(epicIdentifier.toUpperCase());

        // if epic is null, there cannot be an EpicTask that exist
        if(epic == null) {
            throw new EpicNotFoundException("Epic ID: " + epicIdentifier.toUpperCase() + " is not found");
        }

        return epicTaskRepository.findByEpicIdentifierOrderByPriority(epicIdentifier);
    }

    /**
     * Finds an EpicTask object by its identifier.
     *
     * @param  backlog_id     the Backlog Identifier.
     * @param  epicTask_id    the EpicTask Identifier.
     * @return                the EpicTask object.
     */
    public EpicTask findEpicTaskBySequence(String backlog_id, String epicTask_id) {

        EpicTask epicTask = epicTaskRepository.findByEpicSequence(epicTask_id.toUpperCase());

        // if there is no EpicTask that exist
        if(epicTask == null) {
            throw new EpicNotFoundException("Epic Task: " + epicTask_id.toUpperCase() + " is not found");
        }

        // EpicTask must be related to the same Backlog that it exist within
        if(!epicTask.getEpicIdentifier().equals(backlog_id.toUpperCase())){
            throw new EpicNotFoundException("EpicTask ID: " + epicTask_id.toUpperCase() + " does not exist in " + backlog_id);
        }

        return epicTask;
    }

    /**
     * Finds an EpicTask object and updates the object.
     *
     * @param  updateEpicTask    the EpicTask that is being updated.
     * @param  backlog_id        the Backlog Identifier.
     * @param  epicTask_id       the EpicTask Identifier.
     * @return                   the EpicTask object that is updated.
     */
    public EpicTask updateEpicTask(EpicTask updateEpicTask, String backlog_id, String epicTask_id) {

        EpicTask epicTask = findEpicTaskBySequence(backlog_id.toUpperCase(), epicTask_id.toUpperCase());

        epicTask = updateEpicTask;

        return epicTaskRepository.save(epicTask);

    }

    /**
     * Delete an EpicTask object by its identifier if it exists.
     *
     * @param  backlog_id        the Backlog Identifier.
     * @param  epicTask_id       the EpicTask Identifier.
     * @return                   the EpicTask object that is updated.
     */
    public void deleteEpicTask(String backlog_id, String epicTask_id) {

        EpicTask epicTask =  findEpicTaskBySequence(backlog_id,epicTask_id);

       epicTaskRepository.delete(epicTask);
    }
}
