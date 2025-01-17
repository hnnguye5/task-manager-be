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

    @Autowired
    private EpicService epicService;

    /**
     * Creates or Update an EpicTask and returns as an object.
     *
     * @param  epicIdentifier    the Epic Identifier.
     * @param  epicTask          the EpicTask object.
     * @param  username          name of the username that logs in.
     * @return                   the Epic object being saved or updated.
     */
    public EpicTask addEpicTask(String epicIdentifier, EpicTask epicTask, String username) {


            // establish relations with Backlog and Epic Task
            // uses Epic Service to establish if user owns the EpicTask instead of Backlog Repo
            Backlog backlog = epicService.findEpicByIdentifier(epicIdentifier, username).getBacklog();
            epicTask.setBacklog(backlog);

            // allows EpicTask to have its own IDs for each task using Backlog EpicSequence
            Integer backlogSequence = backlog.getEpicSequence();
            backlogSequence++;
            backlog.setEpicSequence(backlogSequence);
            epicTask.setEpicSequence(epicIdentifier + "-" + backlogSequence);
            epicTask.setEpicIdentifier(epicIdentifier);


            if(epicTask.getPriority() == null || epicTask.getPriority() == 0) {
                epicTask.setPriority(3);
            }

            if(epicTask.getStatus() == "" || epicTask.getStatus() ==null) {
                epicTask.setStatus("TO_DO");
            }

            return epicTaskRepository.save(epicTask);

    }

    /**
     * Finds all EpicTask objects that exist.
     *
     * @param  epicIdentifier    the Epic Identifier.
     * @param  username          name of the username that logs in.
     * @return                   list of all EpicTask.
     */
    public Iterable<EpicTask> findBacklogByIdentifier(String epicIdentifier, String username) {

        epicService.findEpicByIdentifier(epicIdentifier, username);

        return epicTaskRepository.findByEpicIdentifierOrderByPriority(epicIdentifier);
    }

    /**
     * Finds an EpicTask object by its identifier.
     *
     * @param  backlog_id     the Backlog Identifier.
     * @param  epicTask_id    the EpicTask Identifier.
     * @param  username       name of the username that logs in.
     * @return                the EpicTask object.
     */
    public EpicTask findEpicTaskBySequence(String backlog_id, String epicTask_id, String username) {

        // finds if backlog exist
        epicService.findEpicByIdentifier(backlog_id, username);

        EpicTask epicTask = epicTaskRepository.findByEpicSequence(epicTask_id);

        if(epicTask == null) {
            throw new EpicNotFoundException("EpicTask " +epicTask_id + " does not exist.");
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
     * @param  username          name of the username that logs in.
     * @return                   the EpicTask object that is updated.
     */
    public EpicTask updateEpicTask(EpicTask updateEpicTask, String backlog_id, String epicTask_id, String username) {

        EpicTask epicTask = findEpicTaskBySequence(backlog_id.toUpperCase(), epicTask_id.toUpperCase(), username);

        epicTask = updateEpicTask;

        return epicTaskRepository.save(epicTask);

    }

    /**
     * Delete an EpicTask object by its identifier if it exists.
     *
     * @param  backlog_id        the Backlog Identifier.
     * @param  epicTask_id       the EpicTask Identifier.
     * @param  username          name of the username that logs in.
     * @return                   the EpicTask object that is updated.
     */
    public void deleteEpicTask(String backlog_id, String epicTask_id, String username) {

        EpicTask epicTask =  findEpicTaskBySequence(backlog_id,epicTask_id, username);

       epicTaskRepository.delete(epicTask);
    }
}
