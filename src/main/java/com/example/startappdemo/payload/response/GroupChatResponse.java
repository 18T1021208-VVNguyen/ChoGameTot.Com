package com.example.startappdemo.payload.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatResponse {

    private UUID id;

    private String name;

    private String avatar;

    private String firstMessage;

    private Timestamp firstDate;

    private Byte typeGroup;

    private Integer numMember;

    private Boolean isSender;

    private Boolean isReceiver;

    private Boolean isSeen;
    private Boolean isOnline;


}
