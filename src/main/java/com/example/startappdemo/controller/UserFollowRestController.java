package com.example.startappdemo.controller;

import com.example.startappdemo.payload.response.UserFollowResponse;
import com.example.startappdemo.service.UserFollowService;
import com.example.startappdemo.validation.CommonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UserFollowRestController {

    @Autowired
    UserFollowService userFollowService;

    @GetMapping("/user-follow")
    public ResponseEntity<?> searchProduct(@RequestParam(name = "user") String idUser) {
        if(!CommonValidation.validateUUID(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("uuid invalide");
        }
        List<UserFollowResponse> userFollowResponse = userFollowService.listAllUserFollow(UUID.fromString(idUser));
        return ResponseEntity.ok().body(userFollowResponse);
    }
}
