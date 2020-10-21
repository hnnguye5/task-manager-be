package com.hoang.jiraclonebe.controller;

import com.hoang.jiraclonebe.domain.Epic;
import com.hoang.jiraclonebe.domain.EpicTask;
import com.hoang.jiraclonebe.repository.BacklogRepository;
import com.hoang.jiraclonebe.service.EpicTaskService;
import com.hoang.jiraclonebe.service.MapErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private EpicTaskService epicTaskService;


    @Autowired
    private MapErrorValidation mapErrorValidation;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addEpicTaskToBacklog(@Valid @RequestBody EpicTask epicTask, BindingResult result,
                                                  @PathVariable String backlog_id) {

        ResponseEntity<?> errorMap = mapErrorValidation.errorMapValidation(result);
        if(errorMap != null) {
            return errorMap;
        }


        EpicTask epicTask1 = epicTaskService.addEpicTask(backlog_id, epicTask);

        return new ResponseEntity<EpicTask>(epicTask1, HttpStatus.CREATED);

    }

    @GetMapping("{backlog_id}")
    public Iterable<EpicTask> getEpicBacklog(@PathVariable String backlog_id) {

       return epicTaskService.findBacklogByIdentifier(backlog_id.toUpperCase());
    }
}