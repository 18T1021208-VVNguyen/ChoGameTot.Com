package com.example.startappdemo.validation;

import com.example.startappdemo.repository.UserRepository;
import com.example.startappdemo.validation.annotation.StringField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class StringFieldValidator  implements ConstraintValidator<StringField, String> {
    private Boolean notBlank;
    private Integer min;
    private Integer max;
    private String messageNotBlank;
    private String messageLength;


    private Boolean checkEmail;
    private Boolean checkEmailExist;

    private Boolean checkUserNameExist;

    private String messageUserNameExist;

    private String messageEmailExist;

    private String messageCheckEmail;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(StringField field) {
        notBlank = field.notBlank();
        messageNotBlank = field.messageNotBlank();
        messageLength = field.messageLength();
        min = field.min();
        max = field.max();

        this.checkEmail = field.checkEmail();
        this.checkEmailExist = field.checkEmailExist();
        this.checkUserNameExist = field.checkUserNameExist();

        this.messageCheckEmail = field.messageCheckEmail();
        this.messageEmailExist = field.messageEmailExist();
        this.messageUserNameExist = field.messageUserNameExist();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (notBlank && value.isBlank()) {
            context.buildConstraintViolationWithTemplate(messageNotBlank).addConstraintViolation();
            return false;
        }
        if ((min > 0 || max < Integer.MAX_VALUE) && (value.length() < min || value.length() > max)) {
            context.buildConstraintViolationWithTemplate(messageLength).addConstraintViolation();
            return false;
        }
        if(checkEmail){
            Pattern pattern = Pattern.compile(CommonValidation.REGEX_EMAIL);
            if(!pattern.matcher(value).matches()){
                context.buildConstraintViolationWithTemplate(messageCheckEmail).addConstraintViolation();
                return false;
            }
        }

        if(checkEmailExist){
            if (userRepository.existsByEmail(value)){
                context.buildConstraintViolationWithTemplate(messageEmailExist).addConstraintViolation();
                return false;
            }
        }
        if(checkUserNameExist){
            if (userRepository.existsByUserName(value)){
                context.buildConstraintViolationWithTemplate(messageUserNameExist).addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
