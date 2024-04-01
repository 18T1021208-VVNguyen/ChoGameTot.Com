package com.example.startappdemo.websocket.interceptor;

import com.example.startappdemo.websocket.common.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class HttpHandshakeTrackingUserOn  implements HandshakeInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandshakeTrackingUserOn.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.info("Call beforeHandshake ");
        String simpSessionID =  Utils.findSimpSessionId(request.getURI().getPath());
        logger.info(request.getURI().toString());
        attributes.put(Utils.SIMP_SESSION_ID_TRACKING_USER_ON,simpSessionID);
        return true;

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
