package com.example.startappdemo.service.impl;

import com.example.startappdemo.entity.SenderReceiverEntity;
import com.example.startappdemo.payload.response.UserFollowResponse;
import com.example.startappdemo.repository.SenderReceiverResponse;
import com.example.startappdemo.repository.dsl.UserFollowDslRepository;
import com.example.startappdemo.service.UserFollowService;
import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserFollowServiceImpl implements UserFollowService {

    @Autowired
    private UserFollowDslRepository userFollowDslRepository;

    @Autowired
    private SenderReceiverResponse senderReceiverResponse;
    @Transactional
    @Override
    public List<UserFollowResponse> listAllUserFollow(UUID userId) {

        List<Tuple> tuples = userFollowDslRepository.findListUserFollow(userId);
        List<UserFollowResponse>  senderResponses = new ArrayList<>();
        List<UUID> userFollowsId = new ArrayList<>();
        for(Tuple item : tuples){
            senderResponses.add(new UserFollowResponse(item.get(0, UUID.class) ,item.get(1, UUID.class) ,
                    item.get(2,String.class), this.getIsOnline(item.get(3,Timestamp.class)) ));
            userFollowsId.add(item.get(1, UUID.class));
        }

         List<SenderReceiverEntity> receiverEntities = senderReceiverResponse.getListSenderReceiverByUserFollow(userId,userFollowsId);
         for(UserFollowResponse ele : senderResponses){
             SenderReceiverEntity check =   receiverEntities.stream().filter(item ->
                  item.getUserSender().getId().toString().equals(ele.getUserFollowId().toString())
                         && item.getUserReceiver().getId().toString().equals(userId.toString())
                     || item.getUserReceiver().getId().toString().equals(ele.getUserFollowId().toString())
                     && item.getUserSender().getId().toString().equals(userId.toString())
             ).findFirst().orElse(null);

             if(check != null){
                 ele.setGroupChatId(check.getGroupChatEntity().getId());
                 ele.setTypeGroup(check.getGroupChatEntity().getTypeGroup());
                 if(check.getUserSender().toString().equals(userId.toString())){
                     ele.setIsReceiver(true);
                 }else{
                     ele.setIsSender(true);
                 }
             }
         }
         return senderResponses;
    }

    private Boolean getIsOnline(Timestamp timeDisconect){

        if(timeDisconect == null){
            return false;
        }
        Long time =   timeDisconect.getTime();
        Long timeNow = new Timestamp(System.currentTimeMillis()).getTime();
        if(Math.abs(timeNow - time) / 1000  <= 5){
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        UserFollowServiceImpl test = new UserFollowServiceImpl();
//        tlistAllUserFollow(UUID.fromString("48b14c39-a3af-489a-aaa8-453abc1d0146"));
    }


}
