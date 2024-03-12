package com.example.startappdemo.service;

import java.util.Map;
import java.util.UUID;

public interface GroupChatService {
    Map<String , Object> getListGroupChat(UUID userId);
}
