package com.hoang.jiraclonebe.controller;

import com.hoang.jiraclonebe.domain.EpicTask;
import com.hoang.jiraclonebe.service.EpicTaskService;
import com.hoang.jiraclonebe.service.MapErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin

/**
 * This class is used for web request handler. It handles Epic Task
 * HTTP Request CRUD operations.
 *
 * @author Hoang Nguyen
 * @version 1.0, 24 Oct 2020
 */
public class BacklogController {

    @Autowired
    private EpicTaskService epicTaskService;


    @Autowired
    private MapErrorValidation mapErrorValidation;

    /**
     * HTTP Request to create an EpicTask.
     *
     * @param  epicTask    the EpicTask object.
     * @param  result      the EpicTask errors creating the object.
     * @param  backlog_id  the Backlog Identifier .
     * @return             the EpicTask object mapping being saved.
     */
    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addEpicTaskToBacklog(@Valid @RequestBody EpicTask epicTask, BindingResult result,
                                                  @PathVariable String backlog_id, Principal principal) {

        ResponseEntity<?> errorMap = mapErrorValidation.errorMapValidation(result);
        // if there are errors creating an EpicTask
        if(errorMap != null) {
            return errorMap;
        }

        EpicTask epicTask1 = epicTaskService.addEpicTask(backlog_id, epicTask, principal.getName());

        return new ResponseEntity<EpicTask>(epicTask1, HttpStatus.CREATED);
    }

    /**
     * HTTP Request to find all EpicTask.
     *
     * @param   backlog_id    the Backlog Identifier.
     * @return                all the existing EpicTask objects.
     */
    @GetMapping("{backlog_id}")
    public Iterable<EpicTask> getEpicBacklog(@PathVariable String backlog_id) {

       return epicTaskService.findBacklogByIdentifier(backlog_id.toUpperCase());
    }

    /**
     * HTTP Request to find a specific EpicTask object by it's identifier.
     *
     * @param  backlog_id     the Backlog Identifier.
     * @param  epicTask_id    the EpicTask Identifier.
     * @return                the specific EpicTask Identifier.
     */
    @GetMapping("/{backlog_id}/{epicTask_id}")
    public ResponseEntity<?> getEpicTask(@PathVariable String backlog_id, @PathVariable String epicTask_id) {

        EpicTask epicTask = epicTaskService.findEpicTaskBySequence(backlog_id,epicTask_id);

        return new ResponseEntity<EpicTask>(epicTask, HttpStatus.OK);
    }

    /**
     * HTTP Request to find a specific EpicTask object and updates the object.
     *
     * @param  epicTask       the EpicTask.
     * @param  result         the EpicTask errors when updating the object.
     * @param  backlog_id     the Backlog Identifier.
     * @param  epicTask_id    the EpicTask Identifier.
     * @return                the EpicTask object that is updated.
     */
    @PutMapping("/{backlog_id}/{epicTask_id}")
    public ResponseEntity<?> updatedEpicTask(@Valid @RequestBody EpicTask epicTask, BindingResult result, @PathVariable
            String backlog_id, @PathVariable String epicTask_id) {

        ResponseEntity<?> errorMap = mapErrorValidation.errorMapValidation(result);
        // if there are errors updating an EpicTask
        if(errorMap != null) {
            return errorMap;
        }

        EpicTask updateEpicTask = epicTaskService.updateEpicTask(epicTask,backlog_id.toUpperCase(),epicTask_id.toUpperCase());

        return new ResponseEntity<EpicTask>(updateEpicTask, HttpStatus.OK);
    }

    /**
     * HTTP Request to delete a specific EpicTask object by it's identifier.
     *
     * @param  backlog_id     the Backlog Identifier.
     * @param  epicTask_id    the Epic Identifier.
     * @return                the message verifying the deletion of the EpicTask object.
     */
    @DeleteMapping("/{backlog_id}/{epicTask_id}")
    public ResponseEntity<?> deleteEpicTask(@PathVariable String backlog_id, @PathVariable String epicTask_id) {

        epicTaskService.deleteEpicTask(backlog_id,epicTask_id);

        return new ResponseEntity<String>("EpicTask " + epicTask_id.toUpperCase() + " was deleted", HttpStatus.OK);
    }

}
