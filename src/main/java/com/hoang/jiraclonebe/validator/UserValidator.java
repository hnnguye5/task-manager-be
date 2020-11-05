package com.hoang.jiraclonebe.validator;

import com.hoang.jiraclonebe.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz); // supports User class to valid
    }

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
