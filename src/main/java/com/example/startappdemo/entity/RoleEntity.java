package com.example.startappdemo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


//@Transactional
@Getter
@Setter
@Entity
@Table(name = "roles", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "role_name") })
public class RoleEntity  extends BaseEntity {


    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserRoleEntity> roleUser = new HashSet<>();

}