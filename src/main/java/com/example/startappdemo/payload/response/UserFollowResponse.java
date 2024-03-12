package com.example.startappdemo.payload.response;

import lombok.*;

import javax.naming.Name;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowResponse {

    private UUID id;
    private String name;
    private UUID userFollow;
    private UUID groupChatId;
    private Boolean isOnline;
    private Boolean isSender;
    private Boolean isReceiver;
    private Byte typeGroup;
    public UserFollowResponse(UUID id ,UUID userFollow, String name  , Boolean isOnline){
        this.id = id;
        this.name = name;
        this.userFollow = userFollow;
        this.isOnline = isOnline;
    }
}
