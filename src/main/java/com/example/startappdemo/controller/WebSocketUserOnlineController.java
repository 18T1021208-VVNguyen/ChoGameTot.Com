package com.example.startappdemo.controller;

import com.example.startappdemo.model.ChatMessage;
import com.example.startappdemo.model.UserModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.List;

public class WebSocketUserOnlineController {

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/publicChatRoom")
//    public List<UserModel> sendUserOnline(){
//
//    }
}
