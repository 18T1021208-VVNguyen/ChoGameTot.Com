package com.example.startappdemo.repository;

import com.example.startappdemo.entity.GroupChatEntity;
import com.example.startappdemo.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface GroupChatRepository  extends JpaRepository<GroupChatEntity, UUID> {

    @Query("""
            SELECT G FROM GroupChatEntity AS G 
            INNER JOIN  SenderReceiverEntity SR  ON G.id = SR.groupChatEntity.id
            WHERE SR.userReceiver.id = :userId and SR.receiverIsDelete is false 
                  OR  SR.userSender.id = :userId and SR.senderIsDelete is false 
            order by G.firstDate DESC 
            """)
    List<GroupChatEntity> getListGroupChat(UUID userId);
}
