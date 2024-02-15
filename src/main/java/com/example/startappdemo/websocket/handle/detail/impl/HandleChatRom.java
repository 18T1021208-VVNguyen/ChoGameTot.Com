package com.example.startappdemo.websocket.handle.detail.impl;

import com.example.startappdemo.model.ChatMessage;
import com.example.startappdemo.websocket.handle.detail.HandleWebSocketConnectDisconnectDetail;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public class HandleChatRom  implements HandleWebSocketConnectDisconnectDetail {

    private StompHeaderAccessor headerAccessor ;
    private SimpMessageSendingOperations messagingTemplate ;

    private static HandleChatRom handleChatRom = null;

    public  static HandleChatRom build(StompHeaderAccessor headerAccessor , SimpMessageSendingOperations messagingTemplate){
        if(handleChatRom == null){
            handleChatRom = new HandleChatRom( headerAccessor ,  messagingTemplate);
        }
        return handleChatRom;
    }

    public HandleChatRom(StompHeaderAccessor headerAccessor , SimpMessageSendingOperations messagingTemplate){
        this.headerAccessor = headerAccessor;
        this.messagingTemplate = messagingTemplate;
    }
    @Override
    public void handleDisconnect() {
        {
            String username = (String) headerAccessor.getSessionAttributes().get("username");
//            logger.info("User Disconnected : " + username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);
            messagingTemplate.convertAndSend("/topic/publicChatRoom", chatMessage);
            headerAccessor.getSessionAttributes().remove("chatRom");
        }
    }

    @Override
    public void handleConnect() {
    }
}
