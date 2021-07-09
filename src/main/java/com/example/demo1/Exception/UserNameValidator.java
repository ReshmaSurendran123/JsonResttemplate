package com.example.demo1.Exception;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UserNameValidator implements ConstraintValidator<UserNameConstraint,List<String>> {
    @Override
    public void initialize(UserNameConstraint userName) {

    }

    @Override
    public boolean isValid(List<String> userName, ConstraintValidatorContext context) {
        //return userName.get(0) != null && userName.get(0).matches("[A-Z]{1}[a-z]*");
        for (int i = 0; i < userName.size(); i++) {
            if (userName.get(i) != null && userName.get(0).matches("[A-Z]{1}[a-z]*")) {
                return true;
            } else {
                return false;
            }
        }
    return false;
    }
}
