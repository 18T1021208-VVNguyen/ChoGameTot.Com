package com.example.startappdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sender_receiver" ,
        uniqueConstraints = { //
                @UniqueConstraint(name = "sender_receiver", columnNames = { "user_sender_id", "user_receiver_id" }) }
)
public class SenderReceiverEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sender_id", nullable = false)
    private UserEntity userSender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_receiver_id", nullable = false)
    private UserEntity userReceiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupchat_id", nullable = false)
    private GroupChatEntity groupChatEntity;


    @Column(name = "sender_delete" , columnDefinition = "boolean default false")
    private Boolean senderIsDelete;

    @Column(name = "receiver_delete" , columnDefinition = "boolean default false")
    private Boolean receiverIsDelete;
}
