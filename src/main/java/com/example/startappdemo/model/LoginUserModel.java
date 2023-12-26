package com.example.startappdemo.model;

import com.example.startappdemo.validation.annotation.StringField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginUserModel {

    @StringField(
            notBlank = true ,
            checkEmail = true,
            checkEmailExist = true
    )
    private String email;

    @StringField(
            notBlank = true ,
            min = 5 , max = 20 , messageLength = "Password phải từ 5 đến 20 kí tự"
    )
    private String password;

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }
}
