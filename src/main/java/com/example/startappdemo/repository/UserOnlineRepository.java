package com.example.startappdemo.repository;

import com.example.startappdemo.entity.UserOnlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserOnlineRepository extends JpaRepository<UserOnlineEntity,Long> {

//        @Query("SELECT t FROM  UserLogTimeEntity  AS t " +
//                "WHERE  t.Ip = ?1 AND t.user IS NULL ")
//        Optional<UserLogTimeEntity> findByIpAddressWithUserIsNull(String ipAddress);

        @Query("SELECT t FROM  UserOnlineEntity  AS t " +
                "WHERE  t.user.id = ?1 ")
        Optional<UserOnlineEntity> findByUser(Long userID);

        @Query("SELECT u FROM UserOnlineEntity as u WHERE u.timeDisconnect is null OR TIMESTAMPDIFF(SECOND , u.timeDisconnect ,NOW())  <= ?1 ")
        List<UserOnlineEntity> findAllUserOnline(int diffMinute);



}
