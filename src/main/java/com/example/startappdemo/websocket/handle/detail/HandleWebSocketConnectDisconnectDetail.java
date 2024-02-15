package com.example.startappdemo.websocket.handle.detail;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public interface HandleWebSocketConnectDisconnectDetail {

    void handleDisconnect();
    void handleConnect();
}
