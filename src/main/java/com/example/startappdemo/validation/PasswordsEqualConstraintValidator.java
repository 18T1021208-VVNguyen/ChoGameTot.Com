package com.example.startappdemo.validation;

import com.example.startappdemo.model.RegisterUserModel;
import com.example.startappdemo.validation.annotation.PasswordsEqualConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements ConstraintValidator<PasswordsEqualConstraint, RegisterUserModel> {

    String message ;


    String messageNotBlank;
    @Override
    public void initialize(PasswordsEqualConstraint constraintAnnotation) {

       messageNotBlank = constraintAnnotation.messageNotBlank();
       message =  constraintAnnotation.message();
    }

    @Override
    public boolean isValid(RegisterUserModel userModel, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if(userModel.getRetypePassword().isBlank() ){
            constraintValidatorContext.buildConstraintViolationWithTemplate(messageNotBlank).addConstraintViolation();
            return  false;
        }
        if(!userModel.getPassword().equals( userModel.getRetypePassword())){
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }
        return true;
    }
}
