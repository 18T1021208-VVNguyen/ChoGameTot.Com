package com.example.startappdemo.repository;

import com.example.startappdemo.entity.GroupChatEntity;
import com.example.startappdemo.entity.SenderReceiverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SenderReceiverResponse extends JpaRepository<SenderReceiverEntity,Long>  {

    @Query("""
        SELECT SR FROM SenderReceiverEntity  as SR
        WHERE SR.userReceiver.id = :userId and SR.receiverIsDelete is false 
                  OR  SR.userSender.id = :userId and SR.senderIsDelete is false  
""")
    List<SenderReceiverEntity> getListSenderReceiverByUserId(Long userId);
}
