package com.example.startappdemo.websocket.config;

import com.example.startappdemo.websocket.interceptor.HttpHandshakeChatRom;
import com.example.startappdemo.websocket.interceptor.HttpHandshakeFollowUserOnOff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer  {

    @Autowired
    private HttpHandshakeChatRom handshakeInterceptorChatRom;

    @Autowired
    private HttpHandshakeFollowUserOnOff httpHandshakeFollowUserOn;


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry ) {

       registry.addEndpoint("/gs-guide-websocket")
               .setHandshakeHandler(defaultHandshakeHandler())
               .addInterceptors(httpHandshakeFollowUserOn);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        gui len
        registry.setApplicationDestinationPrefixes("/app");
//        chu de clien nhan , clien da dk thi se nhan
        registry.enableSimpleBroker("/topic");
    }

    private DefaultHandshakeHandler defaultHandshakeHandler(){
        return new DefaultHandshakeHandler(){
            @Override
            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                Principal principal = request.getPrincipal();
                if(principal == null){
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ANONYMOUS"));
                    principal = new AnonymousAuthenticationToken("WebsocketConfiguration","aaaa",authorities);
                }
                return principal;
            }
        };
    }

}
