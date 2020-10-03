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
import java.util.List;
import java.util.Map;

/**
 * This class is used for web request handler. It handles
 * HTTP Request CRUD operations.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@RestController
@RequestMapping("api/epic")
public class EpicController {

    @Autowired
    private EpicService epicService;

    @Autowired
    private MapErrorValidation mapErrorValidation;

    /**
     * HTTP Request to create or update an object.
     *
     * @param  epic    the Epic object
     * @return         the Epic object mapping being saved or updated.
     */
    @PostMapping("")
    public ResponseEntity<?> createOrUpdateEpic(@RequestBody @Valid Epic epic, BindingResult result) {

        ResponseEntity<Map<String,String>> errorMap = mapErrorValidation.errorMapValidation(result);

        // if there are errors
        if(errorMap != null) {
            return errorMap;
        }

        Epic epic1 = epicService.saveOrUpdate(epic);

        return new ResponseEntity<>(epic1, HttpStatus.CREATED);
    }

    /**
     * HTTP Request to find all Epic.
     *
     * @return         all the existing Epic objects.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllEpic() {

        Iterable<Epic> epic = epicService.findAllEpics();

        return new ResponseEntity<Iterable<Epic>>(epic, HttpStatus.OK);
    }

    /**
     * HTTP Request to find a specific Epic object by it's identifier.
     *
     * @param  epicIdentifier    the Epic identifier
     * @return                   the specific Epic identifier
     */
    @GetMapping("/{epicIdentifier}")
    public ResponseEntity<Epic> getEpicByIdentifier(@PathVariable String epicIdentifier) {

        Epic epic = epicService.findEpicByIdentifier(epicIdentifier.toUpperCase());

        return new ResponseEntity<Epic>(epic, HttpStatus.OK);
    }

    /**
     * HTTP Request to delete a specific Epic object by it's identifier.
     *
     * @param  epicIdentifier    the Epic identifier
     * @return                   the specific Epic identifier
     */

    @DeleteMapping("/{epicIdentifier}")
    public ResponseEntity<?> deleteEpicByIdentifier(@PathVariable String epicIdentifier) {

        epicService.deleteEpicByIdentifier(epicIdentifier.toUpperCase());

        return new ResponseEntity<String>("Epic ID " + epicIdentifier.toUpperCase() + " is deleted", HttpStatus.OK);
    }
}

