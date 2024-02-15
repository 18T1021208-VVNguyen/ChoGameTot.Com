package com.example.startappdemo.websocket.config;

import com.example.startappdemo.websocket.interceptor.HttpHandshakeChatRom;
import com.example.startappdemo.websocket.interceptor.HttpHandshakeTrackingUserOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private HttpHandshakeChatRom handshakeInterceptorChatRom;

    @Autowired
    private HttpHandshakeTrackingUserOn httpHandshakeTrackingUserOn;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry ) {
        registry.addEndpoint("/ws" ).withSockJS().setInterceptors(handshakeInterceptorChatRom);
        registry.addEndpoint("/tr").withSockJS().setInterceptors(httpHandshakeTrackingUserOn);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        gui len
        registry.setApplicationDestinationPrefixes("/app");
//        chu de clien nhan , clien da dk thi se nhan
        registry.enableSimpleBroker("/topic");
    }

}
