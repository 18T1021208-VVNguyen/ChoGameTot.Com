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
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Controller
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @Autowired
    private UserOnlineRepository userOnlineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

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
        headerAccessor.getSessionAttributes().put("userID", userID);
        Optional<UserOnlineEntity> userOnline =  userOnlineRepository.findByUser(UUID.fromString( userID));
        UserOnlineEntity userOnlineEntity = null;
        if(userOnline.isPresent()){
            userOnlineEntity = userOnline.get();
            userOnlineEntity.setTimeConnect(new Timestamp(System.currentTimeMillis()));
            userOnlineEntity.setTimeDisconnect(null);
        }
        else {
            UserEntity userEntity =  userRepository.findById(UUID.fromString(userID))
                    .orElseThrow( () -> new RuntimeException("Error: userID not found: " + userID));
            userOnlineEntity = UserOnlineEntity.builder()
                    .user(userEntity)
                    .timeConnect(new Timestamp(System.currentTimeMillis()))
                    .timeDisconnect(null)
                    .build();
        }
        userOnlineRepository.save(userOnlineEntity);
        messagingTemplate.convertAndSend("/topic/checkOnline/"+userID, true);
    }
}
