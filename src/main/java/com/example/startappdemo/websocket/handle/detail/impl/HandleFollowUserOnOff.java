package com.example.startappdemo.websocket.handle.detail.impl;

import com.example.startappdemo.entity.UserOnlineEntity;
import com.example.startappdemo.repository.UserOnlineRepository;
import com.example.startappdemo.websocket.common.Utils;
import com.example.startappdemo.websocket.handle.detail.HandleWebSocketConnectDisconnectDetail;
import com.example.startappdemo.websocket.schedule.DelayNotifiUserOnOff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.sql.Timestamp;
import java.util.UUID;

public class HandleFollowUserOnOff implements HandleWebSocketConnectDisconnectDetail {

    private StompHeaderAccessor headerAccessor ;
    private ApplicationContext applicationContext;

    private SimpMessageSendingOperations messagingTemplate ;

    private static final Logger logger = LoggerFactory.getLogger(HandleFollowUserOnOff.class);

    public HandleFollowUserOnOff(StompHeaderAccessor headerAccessor , ApplicationContext applicationContext , SimpMessageSendingOperations messagingTemplate ){
        this.applicationContext = applicationContext;
        this.headerAccessor = headerAccessor;
        this.messagingTemplate = messagingTemplate;

    }

    @Override
    public void handleDisconnect() {
        {
            UserOnlineRepository userOnlineRepository =   applicationContext.getBean(UserOnlineRepository.class);
            String userID = headerAccessor.getSessionAttributes().get(Utils.USER_ID).toString();
            UserOnlineEntity userOnlineEntity =  userOnlineRepository.findByUser(UUID.fromString( userID))
                    .orElseThrow(() -> new RuntimeException("Error: userID not found: " + userID));

            Timestamp timeDisconnect =  new Timestamp(System.currentTimeMillis());
            userOnlineEntity.setTimeDisconnect(timeDisconnect);

            userOnlineRepository.save(userOnlineEntity);
//            khi disconnect tao re 1 thread , khoang 4s gui ve client , la kq, han on hay off.
            DelayNotifiUserOnOff sendUserOnOff = new DelayNotifiUserOnOff( messagingTemplate,userID , userOnlineRepository);
            new Thread(sendUserOnOff).start();
            headerAccessor.getSessionAttributes().clear();

        }
    }

    @Override
    public void handleConnect() {

    }
}
