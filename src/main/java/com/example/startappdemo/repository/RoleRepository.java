package com.example.startappdemo.repository;

import com.example.startappdemo.entity.RoleEntity;
import com.example.startappdemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository  extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByRoleName(String roleName);


//    @Query(nativeQuery = true, value="SELECT r.* FROM roles AS r " +
//            "INNER JOIN user_role  AS ur ON r.role_id = ur.role_id " +
//            "WHERE ur.user_id = 1?")
//    Set<RoleEntity> findRoleByUserId(Long userId);
}
