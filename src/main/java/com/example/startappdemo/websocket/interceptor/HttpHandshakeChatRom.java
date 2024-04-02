package com.example.startappdemo.websocket.interceptor;

import com.example.startappdemo.websocket.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class HttpHandshakeChatRom implements HandshakeInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandshakeChatRom.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.info("Call beforeHandshake Chat Rom");
//        String simpSessionID =  Utils.findSimpSessionId(request.getURI().getPath());
//        attributes.put(Utils.KEY_HANDLE_WEBSOCKET_DISCONNECT,Utils.VALUE_FOLLOW_USER_ON_OFF);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        logger.info("Call afterHandshake");

    }
}
