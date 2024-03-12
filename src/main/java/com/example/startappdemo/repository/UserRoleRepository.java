package com.example.startappdemo.repository;

import com.example.startappdemo.entity.RoleEntity;
import com.example.startappdemo.entity.UserEntity;
import com.example.startappdemo.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserRoleRepository  extends JpaRepository<UserRoleEntity, UUID> {
        Set<UserRoleEntity> findAllByUser(UserEntity user);

}
