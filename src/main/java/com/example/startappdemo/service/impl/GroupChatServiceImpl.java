package com.example.startappdemo.service.impl;

import com.example.startappdemo.entity.GroupChatEntity;
import com.example.startappdemo.entity.SenderReceiverEntity;
import com.example.startappdemo.payload.response.GroupChatResponse;
import com.example.startappdemo.repository.GroupChatRepository;
import com.example.startappdemo.repository.SenderReceiverResponse;
import com.example.startappdemo.service.GroupChatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupChatServiceImpl implements GroupChatService {

    @Autowired
    private GroupChatRepository groupChatRepository;

    @Autowired
    private SenderReceiverResponse senderReceiverResponse;

    @Transactional
    @Override
    public Map<String, Object> getListGroupChat(UUID userId) {
        List<SenderReceiverEntity> senderReceiverEntityList = senderReceiverResponse.getListSenderReceiverByUserId(userId);
        List<GroupChatEntity> groupChatEntityList = groupChatRepository.getListGroupChat(userId);
        List<GroupChatResponse> groupChatResponseList = new ArrayList<>();
        Integer countNotSeen = 0;

        for (GroupChatEntity e : groupChatEntityList) {
            GroupChatResponse.GroupChatResponseBuilder groupChatResponseBuilder =
                    GroupChatResponse.builder()
                            .id(e.getId())
                            .isSeen(e.getIsSeen())
                            .firstMessage(e.getFirstMessage())
                            .firstDate(e.getFirstDate())
                            .numMember(e.getNumMember())
                            .typeGroup(e.getTypeGroup());

            if(e.getTypeGroup().byteValue() == 1){

                SenderReceiverEntity entitySenderReceiver = senderReceiverEntityList.stream().filter(
                        item-> item.getGroupChatEntity().getId().toString().equals(e.getId().toString())
                ).findFirst().orElseThrow(() -> new UsernameNotFoundException("groupChatEntity Not Found : " +  e.getId() ));
//                la nguoi gui
                if( entitySenderReceiver.getUserSender().getId().toString().equals( userId.toString()) ){
                    groupChatResponseBuilder.avatar(entitySenderReceiver.getUserSender().getAvatar())
                            .name(entitySenderReceiver.getUserSender().getUserName())
                            .isSender(true);
                }else{
                    groupChatResponseBuilder.avatar(entitySenderReceiver.getUserReceiver().getAvatar())
                            .name(entitySenderReceiver.getUserReceiver().getUserName())
                            .isReceiver(true);
                }

            }else{
                System.out.println("Chua xu ly");
            }
            groupChatResponseList.add(groupChatResponseBuilder.build());

            if(!e.getIsSeen() )
                countNotSeen++;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("notSeen" , countNotSeen);
        result.put("groupsChat" ,groupChatResponseList );
        return result;
    }

    public static void main(String[] args) {
        GroupChatServiceImpl groupChatService = new GroupChatServiceImpl();
//        groupChatService.getListGroupChat(12L);
    }
}
