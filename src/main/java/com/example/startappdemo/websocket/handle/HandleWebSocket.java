package com.example.startappdemo.websocket.handle;

import com.example.startappdemo.websocket.common.Utils;
import com.example.startappdemo.websocket.handle.detail.HandleWebSocketConnectDisconnectDetail;
import com.example.startappdemo.websocket.handle.detail.impl.HandleFollowUserOnOff;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
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

    private  StompHeaderAccessor headerAccessorInput;

//    send to client
    @Autowired
    private SimpMessageSendingOperations messagingTemplateOutPut;

    public void init(StompHeaderAccessor headerAccessor  ){
        this.headerAccessorInput = headerAccessor;
        this.createInstanceHandle();

    }


    private void createInstanceHandle(){
        Object objectCase =
                ObjectUtils.defaultIfNull( headerAccessorInput.getSessionAttributes().get(Utils.KEY_HANDLE_WEBSOCKET_DISCONNECT) , "-1");
        Integer caseOption = Integer.valueOf(objectCase.toString());
        switch (caseOption){
            case 0 :
                break;
            case 1 :
                this.handleWebSocketConnectDisconnectDetail = new HandleFollowUserOnOff(this.headerAccessorInput , this.applicationContext , messagingTemplateOutPut);
                break;
            default:
                break;
        }
    }

    public void handleDisconnect(){
        checkNull();
        handleWebSocketConnectDisconnectDetail.handleDisconnect();
    }
    public void handleConnect(){
        checkNull();
        handleWebSocketConnectDisconnectDetail.handleConnect();
    }

    private void checkNull(){
        if(this.handleWebSocketConnectDisconnectDetail == null)
        {
             throw new RuntimeException("webSocket not found instant to handle disconnect");
        }
    }




}
