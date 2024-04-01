package com.example.startappdemo.websocket.handle;

import com.example.startappdemo.websocket.common.Utils;
import com.example.startappdemo.websocket.handle.detail.HandleWebSocketConnectDisconnectDetail;
import com.example.startappdemo.websocket.handle.detail.impl.HandleChatRom;
import com.example.startappdemo.websocket.handle.detail.impl.HandleTrackingUserOnline;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;



@Component
@NoArgsConstructor
public class HandleWebSocket {



    @Autowired
    private ApplicationContext applicationContext;
    private HandleWebSocketConnectDisconnectDetail handleWebSocketConnectDisconnectDetail;

//    input
    private  StompHeaderAccessor headerAccessorInput;

    @Autowired
    private SimpMessageSendingOperations messagingTemplateOutPut;

    public void init(StompHeaderAccessor headerAccessor  ){
        this.headerAccessorInput = headerAccessor;
        this.createInstanceHandle();

    }


    private void createInstanceHandle(){
        int caseOption = this.getOption();
        switch (caseOption){
            case 0 :
                this.handleWebSocketConnectDisconnectDetail = new HandleChatRom(headerAccessorInput , messagingTemplateOutPut);
                break;
            case 1 :
                this.handleWebSocketConnectDisconnectDetail = new HandleTrackingUserOnline (this.headerAccessorInput , this.applicationContext , messagingTemplateOutPut);
                break;
            default:
                break;
        }
    }
    private  int getOption( ){
        String sessionIDChatRom = (String) headerAccessorInput.getSessionAttributes().get(Utils.SIMP_SESSION_ID_CHAT_ROM);
        String username = (String) headerAccessorInput.getSessionAttributes().get("username");

        if(sessionIDChatRom != null  && sessionIDChatRom.equals(headerAccessorInput.getSessionId()) && username != null){
            return 0;
        }

        String sessionIDTrackingUserOnline = (String) headerAccessorInput.getSessionAttributes().get(Utils.SIMP_SESSION_ID_TRACKING_USER_ON);

        if (sessionIDTrackingUserOnline != null && sessionIDTrackingUserOnline.equals(headerAccessorInput.getSessionId())){
            return 1;
        }

        return -1;

    }
    public void handleDisconnect(){
        handleWebSocketConnectDisconnectDetail.handleDisconnect();
    }
    public void handleConnect(){
        handleWebSocketConnectDisconnectDetail.handleConnect();
    }




}
