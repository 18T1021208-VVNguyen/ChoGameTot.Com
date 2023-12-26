package com.example.startappdemo.repository;

import com.example.startappdemo.entity.UserLogTimeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserLogTimeRepository extends JpaRepository<UserLogTimeEntity,Long> {

//        @Query("SELECT t FROM  UserLogTimeEntity  AS t " +
//                "WHERE  t.Ip = ?1 AND t.user IS NULL ")
//        Optional<UserLogTimeEntity> findByIpAddressWithUserIsNull(String ipAddress);

        @Query("SELECT t FROM  UserLogTimeEntity  AS t " +
                "WHERE  t.user.id = ?1 ")
        Optional<UserLogTimeEntity> findByUser(Long userID);

//        @Modifying
//        @Transactional
//        @Query("DELETE FROM  UserLogTimeEntity  AS t " +
//                "WHERE  t.Ip = ?1 AND t.user IS NULL ")
//        void deleteByIP(String Ip);

}
