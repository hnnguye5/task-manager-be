package com.hoang.jiraclonebe.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * The class is handles the constraint if the object
 * does not meet the criteria.
 *
 * @author Hoang Nguyen
 * @version 1.0, 3 Oct 2020
 */
@Service
public class MapErrorValidation {

    /**
     * Handles error messages if object does not meet criteria.
     *
     * @param  result    binding result errors
     * @return           constraint error message if not met the criteria.
     */
    public ResponseEntity<Map<String,String>> errorMapValidation(BindingResult result) {

        // if constraints is not met
        if(result.hasErrors()){

            Map<String, String> errorMap = new HashMap<String,String>();

            // get errors and map the value to field to return custom message
            for(FieldError errors: result.getFieldErrors()) {
                errorMap.put(errors.getField(), errors.getDefaultMessage());
            }

            return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
