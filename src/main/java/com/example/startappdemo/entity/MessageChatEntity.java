package com.example.startappdemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "message_chat")
public class MessageChatEntity extends  BaseEntity{

    @Column(name = "content_message")
    @NotBlank
    private String contentMessage;

    @Column(name = "first_message")
    @NotBlank
    private String firstMessage;

    @Column(name = "first_date")
    @NotNull
    private Timestamp firstDate;

    @Column(name = "number_message")
    @NotNull
    private Integer numberMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupchat_id", nullable = false)
    private GroupChatEntity groupChat;
}
