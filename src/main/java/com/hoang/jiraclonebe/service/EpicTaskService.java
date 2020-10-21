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

@Service
public class EpicTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private EpicTaskRepository epicTaskRepository;

    @Autowired
    private EpicRepository epicRepository;

    public EpicTask addEpicTask(String epicIdentifier, EpicTask epicTask) {

        try{
            // connect Backlog with Epic Task
            Backlog backlog = backlogRepository.findByEpicIdentifier((epicIdentifier.toUpperCase()));
            epicTask.setBacklog(backlog);

            // allows Epic Task to have its own IDs for each task
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

    public Iterable<EpicTask> findBacklogByIdentifier(String epicIdentifier) {

        Epic epic = epicRepository.findByEpicIdentifier(epicIdentifier.toUpperCase());

        if(epic == null) {
            throw new EpicNotFoundException("Epic ID: " + epicIdentifier.toUpperCase() + " is not found");
        }

        return epicTaskRepository.findByEpicIdentifierOrderByPriority(epicIdentifier);
    }

    public EpicTask findEpicTaskBySequence(String backlog_id, String epicTask_id) {

        return epicTaskRepository.findByEpicSequence(epicTask_id);
    }
}
