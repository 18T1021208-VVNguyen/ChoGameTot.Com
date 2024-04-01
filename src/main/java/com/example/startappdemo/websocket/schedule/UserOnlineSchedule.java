//package com.example.startappdemo.websocket.schedule;
//
//import com.example.startappdemo.entity.UserOnlineEntity;
//import com.example.startappdemo.repository.UserOnlineRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserOnlineSchedule {
//
//    @Autowired
//    private UserOnlineRepository userOnlineRepository;
//
//    @Autowired
//    private SimpMessageSendingOperations messagingTemplate;
//
//    @Scheduled(fixedRate = 500)
//    public void sendListUserOnline(){
//        List<UserOnlineEntity> userOnlineEntityList = userOnlineRepository.findAllUserOnline(1);
//        messagingTemplate.convertAndSend("/topic/listUserOnline", userOnlineEntityList);
//    }
//}
