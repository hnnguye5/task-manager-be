package com.hoang.jiraclonebe.controller;

import com.hoang.jiraclonebe.domain.Epic;
import com.hoang.jiraclonebe.service.EpicService;
import com.hoang.jiraclonebe.service.MapErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
     * @param  epic    the epic object
     * @return         the epic object mapping being saved or updated.
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
}
