package com.example.startappdemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "group_chat")
public class GroupChatEntity  extends BaseEntity{

    @Column(name = "name" )
    @NotBlank
    private String name;

    @Column(name = "type_group" )
    @NotNull
    private Byte typeGroup;

    @Column(name = "number_member" )
    @NotNull
    private Integer numMember;

//    Da xem
    @Column(name = "is_seen" , columnDefinition = "boolean default false" )
    private Boolean isSeen;

    @Column(name = "is_hidden" , columnDefinition = "boolean default false" )
    private Boolean isHidden;

    @Column(name = "first_message")
    @NotBlank
    private String firstMessage;

    @Column(name = "first_date")
    @NotNull
    private Timestamp firstDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupChat")
    private Set<MessageChatEntity> messageChatEntity  = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupChatEntity")
    private Set<SenderReceiverEntity> senderReceiverEntity = new HashSet<>();

}
