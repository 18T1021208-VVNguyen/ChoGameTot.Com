package com.example.startappdemo.controller;

import com.example.startappdemo.model.ChatMessage;
import com.example.startappdemo.repository.GroupChatRepository;
import com.example.startappdemo.security.service.impl.UserDetailsImpl;
import com.example.startappdemo.service.GroupChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Controller
public class WebSocketChatMessageController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private GroupChatService groupChatService;
    @MessageMapping("list/groupChat")
    public void addUser(@Payload String userId, SimpMessageHeaderAccessor headerAccessor , Principal principal){
        UserDetailsImpl u =  (UserDetailsImpl) ((Authentication) principal).getPrincipal();
        if(u.getId().toString().equals(userId)) {
            throw new RuntimeException("user Id not match");
        }
        Map<String ,Object> result =  groupChatService.getListGroupChat(u.getId());
        messagingTemplate.convertAndSend("list/groupChat/" + userId, result);
    }
}
