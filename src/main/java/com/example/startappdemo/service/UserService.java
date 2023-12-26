package com.example.startappdemo.service;

import com.example.startappdemo.model.RegisterUserModel;


public interface UserService {
    void saveUser(RegisterUserModel model);
    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);


}
