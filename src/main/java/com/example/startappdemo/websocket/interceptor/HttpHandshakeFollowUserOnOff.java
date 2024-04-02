package com.example.startappdemo.websocket.interceptor;

import com.example.startappdemo.websocket.common.Utils;
import com.example.startappdemo.websocket.handle.HandleWebSocket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.Random;

@Component
public class HttpHandshakeFollowUserOnOff implements HandshakeInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandshakeFollowUserOnOff.class);
    private final Random rand = new Random();

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.info("Call beforeHandshake ");;
        logger.info(request.getURI().toString());
        attributes.put(Utils.KEY_HANDLE_WEBSOCKET_DISCONNECT,Utils.VALUE_FOLLOW_USER_ON_OFF);
//        HttpSession session = ((HttpServletRequest) request).getSession(true);
//        Object value = session.getAttribute(Utils.KEY_HANDLE_WEBSOCKET_DISCONNECT);
//        handleWebSocket.init(attributes);
        return true;

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
