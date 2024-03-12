package com.example.startappdemo.repository;

import com.example.startappdemo.entity.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserFollowEntityRepository extends JpaRepository<UserFollowEntity, UUID> {
    @Query("""
    SELECT U FROM UserFollowEntity AS U WHERE U.userEntity.id = :userId
    """)
    List<UserFollowEntity> findByUserEntity(UUID userId);
}
