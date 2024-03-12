package com.example.startappdemo.service;

import com.example.startappdemo.payload.response.UserFollowResponse;

import java.util.List;
import java.util.UUID;

public interface UserFollowService {
    List<UserFollowResponse> listAllUserFollow(UUID userId);
}
