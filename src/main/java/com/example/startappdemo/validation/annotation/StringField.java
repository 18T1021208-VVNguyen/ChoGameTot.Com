package com.example.startappdemo.validation.annotation;

import com.example.startappdemo.validation.StringFieldValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = StringFieldValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringField {


    String message() default "Wrong data of string field";

    String messageNotBlank() default "Field này là bắt buộc nhập !";

    String messageLength() default "Độ dài không hợp lệ ! ";

    String messageUserNameExist() default "UserName đã tồn tại !";

    String messageEmailExist() default "Email đã tồn tại !";

    String messageCheckEmail() default "Email không hợp lệ !";

    boolean checkEmail() default  false;

    boolean checkUserNameExist() default false;

    boolean checkEmailExist() default  false;

    boolean notBlank() default false;

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
