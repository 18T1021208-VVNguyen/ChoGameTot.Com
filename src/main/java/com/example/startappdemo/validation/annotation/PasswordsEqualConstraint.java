package com.example.startappdemo.validation.annotation;

import com.example.startappdemo.validation.PasswordsEqualConstraintValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordsEqualConstraintValidator.class)
public @interface PasswordsEqualConstraint {
//    String name() default "retypePassword";
    String message() default "";
    String messageNotBlank() default "Field can't be empty";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
