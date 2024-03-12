package com.example.startappdemo.websocket.handle.detail.impl;

import com.example.startappdemo.entity.UserOnlineEntity;
import com.example.startappdemo.repository.UserOnlineRepository;
import com.example.startappdemo.websocket.handle.detail.HandleWebSocketConnectDisconnectDetail;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.sql.Timestamp;

public class HandleTrackingUserOnline  implements HandleWebSocketConnectDisconnectDetail {

    private StompHeaderAccessor headerAccessor ;
    private ApplicationContext applicationContext;

    private static HandleTrackingUserOnline handleTrackingUserOnline = null;

    public  static HandleTrackingUserOnline build(StompHeaderAccessor headerAccessor , ApplicationContext applicationContext){
        if(handleTrackingUserOnline == null){
            handleTrackingUserOnline = new HandleTrackingUserOnline( headerAccessor ,  applicationContext);
        }
        return handleTrackingUserOnline;
    }


    public HandleTrackingUserOnline(StompHeaderAccessor headerAccessor ,ApplicationContext applicationContext ){
        this.applicationContext = applicationContext;
        this.headerAccessor = headerAccessor;
    }
    @Override
    public void handleDisconnect() {
        {
           UserOnlineRepository  userOnlineRepository =   applicationContext.getBean(UserOnlineRepository.class);
            // xu ly DB , thoi gian disconect
            Long userID =  (Long) headerAccessor.getSessionAttributes().get("userID");
            UserOnlineEntity userOnlineEntity =  userOnlineRepository.findByUser(userID)
                    .orElseThrow(() -> new RuntimeException("Error: userID not found: " + userID));

            userOnlineEntity.setTimeDisconnect(new Timestamp(System.currentTimeMillis()));

            userOnlineRepository.save(userOnlineEntity);

            headerAccessor.getSessionAttributes().remove("trackingUserOnline");

        }
    }

    @Override
    public void handleConnect() {

    }
}
