package com.example.startappdemo.controller;

import com.example.startappdemo.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatMessageController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @MessageMapping("/chat.listGroupChat")
    @SendTo("/topic/listGroupChat")
    public ChatMessage addUser(@Payload String userId, SimpMessageHeaderAccessor headerAccessor){

        return null;
    }
}
