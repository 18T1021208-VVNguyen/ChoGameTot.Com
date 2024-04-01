package com.example.startappdemo.websocket.schedule;

import com.example.startappdemo.common.CommonUtils;
import com.example.startappdemo.entity.UserOnlineEntity;
import com.example.startappdemo.repository.UserOnlineRepository;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import static com.example.startappdemo.common.CommonUtils.disconnectionInterval;

public class DelayNotifiUserOnOff  implements Runnable {

    private SimpMessageSendingOperations messagingTemplate;
    private String idUserSub ;

    private UserOnlineRepository userOnlineRepository;

    public DelayNotifiUserOnOff( SimpMessageSendingOperations messagingTemplate , String idUserSub , UserOnlineRepository userOnlineRepository){
        this.messagingTemplate = messagingTemplate;
        this.idUserSub = idUserSub;
        this.userOnlineRepository = userOnlineRepository;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            // time trong db ra noi moi dung.
            UserOnlineEntity userOnlineEntity =  userOnlineRepository.findByUser(UUID.fromString( this.idUserSub))
                    .orElseThrow(() -> new RuntimeException("Error: userID not found: " + this.idUserSub));

            boolean isOnline = CommonUtils.getIsOnline(userOnlineEntity.getTimeDisconnect());
            messagingTemplate.convertAndSend("/topic/checkOnline/"+idUserSub, isOnline);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
