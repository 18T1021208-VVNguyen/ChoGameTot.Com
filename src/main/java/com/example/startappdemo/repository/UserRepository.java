package com.example.startappdemo.repository;

import com.example.startappdemo.entity.RoleEntity;
import com.example.startappdemo.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;



public interface  UserRepository  extends JpaRepository<UserEntity,Long> {


    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByEmail(String email);

}
