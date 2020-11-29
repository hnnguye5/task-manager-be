package com.hoang.jiraclonebe.controller;

import com.hoang.jiraclonebe.domain.Epic;
import com.hoang.jiraclonebe.service.EpicService;
import com.hoang.jiraclonebe.service.MapErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

/**
 * This class is used for web request handler. It handles Epic
 * HTTP Request CRUD operations.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@RestController
@RequestMapping("api/epic")
@CrossOrigin
public class EpicController {

    @Autowired
    private EpicService epicService;

    @Autowired
    private MapErrorValidation mapErrorValidation;

    /**
     * HTTP Request to create or update an Epic.
     *
     * @param  epic         the Epic object.
     * @param  result       the Epic errors creating the object.
     * @param  principal    the username that logs in.
     * @return              the Epic object mapping being saved or updated.
     */
    @PostMapping("")
    public ResponseEntity<?> createOrUpdateEpic(@RequestBody @Valid Epic epic, BindingResult result, Principal principal) {

        ResponseEntity<Map<String,String>> errorMap = mapErrorValidation.errorMapValidation(result);
        // if there are errors creating an Epic
        if(errorMap != null) {
            return errorMap;
        }

        // principal sets relationship to user and epic
        Epic epic1 = epicService.saveOrUpdate(epic, principal.getName());

        return new ResponseEntity<>(epic1, HttpStatus.CREATED);
    }

    /**
     * HTTP Request to find all Epic.
     *
     * @param  principal    username that logs in.
     * @return              all the existing Epic objects.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllEpic(Principal principal) {

        Iterable<Epic> epic = epicService.findAll(principal.getName());

        return new ResponseEntity<Iterable<Epic>>(epic, HttpStatus.OK);
    }

    /**
     * HTTP Request to find a specific Epic object by it's identifier.
     *
     * @param  epicIdentifier    the Epic Identifier.
     * @param  principal         username that logs in.
     * @return                   the specific Epic Identifier.
     */
    @GetMapping("/{epicIdentifier}")
    public ResponseEntity<Epic> getEpicByIdentifier(@PathVariable String epicIdentifier, Principal principal) {

        Epic epic = epicService.findEpicByIdentifier(epicIdentifier.toUpperCase(), principal.getName());

        return new ResponseEntity<Epic>(epic, HttpStatus.OK);
    }

    /**
     * HTTP Request to delete a specific Epic object by it's Identifier.
     *
     * @param  epicIdentifier    the Epic Identifier.
     * @return                   the specific Epic Identifier.
     */
    @DeleteMapping("/{epicIdentifier}")
    public ResponseEntity<?> deleteEpicByIdentifier(@PathVariable String epicIdentifier, Principal principal) {

        epicService.deleteEpicByIdentifier(epicIdentifier.toUpperCase(), principal.getName());

        return new ResponseEntity<String>("Epic ID " + epicIdentifier.toUpperCase() + " is deleted", HttpStatus.OK);
    }
}

