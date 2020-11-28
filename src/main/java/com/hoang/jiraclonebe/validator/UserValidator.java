package com.hoang.jiraclonebe.validator;

import com.hoang.jiraclonebe.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * The class handles the validation of the new User being created.
 *
 * @author Hoang Nguyen
 * @version 1.0, 7 Nov 2020
 */
@Component
public class UserValidator implements Validator {

    /**
     * Finds the class to validate.
     *
     * @param  clazz    the class of the User to validate.
     * @return          the class of the User to validate.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz); // supports User class to valid
    }

    /**
     * The rules to be able to create a new user.
     *
     * @param  target    the User class to validate
     * @param  errors    the errors if the user does not meet requirements when creating a new user
     */
    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if(user.getPassword().length() < 8) {
            errors.rejectValue("password", "Length", "Password must be at least 8 characters");
        }

        // test strings password
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Password does not match. Please try again");
        }
    }
}
