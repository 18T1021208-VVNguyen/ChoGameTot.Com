package com.example.startappdemo.controller;

import com.example.startappdemo.entity.UserEntity;
import com.example.startappdemo.entity.UserOnlineEntity;
import com.example.startappdemo.model.ChatMessage;
import com.example.startappdemo.repository.UserOnlineRepository;
import com.example.startappdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Optional;

@Controller
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @Autowired
    private UserOnlineRepository userOnlineRepository;

    @Autowired
    private UserRepository userRepository;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        logger.info(String.valueOf(chatMessage));
        return chatMessage;
    }


    @MessageMapping("/tracking.userOnline")
    public  void trackingUserOnline(@Payload String userID , SimpMessageHeaderAccessor headerAccessor ){
        headerAccessor.getSessionAttributes().put("userID", Long.valueOf(userID));
        Optional<UserOnlineEntity> userOnline =  userOnlineRepository.findByUser(Long.valueOf(userID));
        UserOnlineEntity userOnlineEntity = null;
        if(userOnline.isPresent()){
            userOnlineEntity = userOnline.get();
            userOnlineEntity.setTimeConnect(new Date());
            userOnlineEntity.setTimeDisconnect(null);
        }
        else {
            UserEntity userEntity =  userRepository.findById(Long.valueOf(userID))
                    .orElseThrow( () -> new RuntimeException("Error: userID not found: " + userID));
            userOnlineEntity = UserOnlineEntity.builder()
                    .user(userEntity)
                    .timeConnect(new Date())
                    .timeDisconnect(null)
                    .build();
        }

        userOnlineRepository.save(userOnlineEntity);
    }
}
