package com.example.startappdemo.repository;

import com.example.startappdemo.entity.GroupChatEntity;
import com.example.startappdemo.entity.SenderReceiverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SenderReceiverResponse extends JpaRepository<SenderReceiverEntity, UUID>  {

    @Query("""
        SELECT SR FROM SenderReceiverEntity  as SR
        WHERE SR.userReceiver.id = :userId and SR.receiverIsDelete = false 
                  OR  SR.userSender.id = :userId and SR.senderIsDelete = false  
""")
    List<SenderReceiverEntity> getListSenderReceiverByUserId(UUID userId);

    /*Get Group chat giua user flows va user id*/
    @Query("""
                SELECT SR FROM SenderReceiverEntity  as SR
        WHERE SR.userReceiver.id = :userId and SR.receiverIsDelete = false AND SR.userSender.id IN :userFollowId
               OR  SR.userSender.id = :userId and SR.senderIsDelete = false  AND SR.userReceiver.id IN :userFollowId
""")
    List<SenderReceiverEntity> getListSenderReceiverByUserFollow(UUID userId , List<UUID> userFollowId);
}
