package com.example.startappdemo.validation;

import java.util.regex.Pattern;

public class CommonValidation {

    public  static final String PASSWORD_NOT_BLANK = "Nhập mật khẩu là bắt buộc";
    public static  final String PASSWORD_NOT_MATCH = "Nhập lại password không khớp !";

    public static final  Pattern UUID_REGEX =
             Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public  static  final String REGEX_EMAIL = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static Boolean validateUUID(String uuid){
        if(uuid == null || uuid.isBlank()){
            return false;
        }
        if(!UUID_REGEX.matcher(uuid).matches()){
            return false;
        }
        return  true;

    }
}
