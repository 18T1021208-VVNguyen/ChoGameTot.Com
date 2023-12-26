package com.example.startappdemo.model;

import com.example.startappdemo.validation.CommonValidation;
import com.example.startappdemo.validation.annotation.PasswordsEqualConstraint;
import com.example.startappdemo.validation.annotation.StringField;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@PasswordsEqualConstraint(
        messageNotBlank = CommonValidation.PASSWORD_NOT_BLANK,
        message = CommonValidation.PASSWORD_NOT_MATCH)
public class RegisterUserModel {

    @StringField(
            notBlank = true ,
            checkEmail = true,
            checkEmailExist = true
    )
    private String email;


    @StringField(
            notBlank = true ,
            min = 5 , max = 20 , messageLength = "userName phải từ 5 đến 20 kí tự" ,
            checkUserNameExist = true
    )
    private String userName;

    @StringField(
            notBlank = true ,
            min = 5 , max = 20 , messageLength = "Password phải từ 5 đến 20 kí tự"
    )
    private String password;

    private String retypePassword;



    public void setEmail(String email) {
        this.email = email.trim();
    }

    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword.trim();
    }
}
