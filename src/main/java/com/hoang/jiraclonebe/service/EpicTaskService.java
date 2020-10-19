package com.hoang.jiraclonebe.service;

import com.hoang.jiraclonebe.domain.Backlog;
import com.hoang.jiraclonebe.domain.EpicTask;
import com.hoang.jiraclonebe.repository.BacklogRepository;
import com.hoang.jiraclonebe.repository.EpicTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpicTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private EpicTaskRepository epicTaskRepository;

    public EpicTask addEpicTask(String epicIdentifier, EpicTask epicTask) {

        // connect Backlog with Epic Task
        Backlog backlog = backlogRepository.findByEpicIdentifier((epicIdentifier));
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
}
