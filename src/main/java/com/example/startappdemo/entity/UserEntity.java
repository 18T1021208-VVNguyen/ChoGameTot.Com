package com.example.startappdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_EMAIL", columnNames = "email"),
        @UniqueConstraint(name = "UNIQUE_UserName", columnNames = "userName"),

 })
public class UserEntity extends  BaseEntity {


    @Column(name = "email" )
    @Email
    @NotBlank
    private  String email;

    @NotBlank
    @Column(name = "userName" )
    private String userName;

    @NotBlank
    @Column(name = "password" )
    private  String password;

    @Column(name = "birth_day" )
    private Timestamp birthDay;

    @Column(name = "avatar" )
    private String avatar;

    @Column(name = "address" )
    private String address;

//    so tien can lai
    @Column(name = "balance")
    private Float balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<UserRoleEntity> roleUser = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userReceiver")
    private Set<SenderReceiverEntity> userReceiver = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userSender")
    private Set<SenderReceiverEntity> userSender = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private Set<UserFollowEntity> userEntity = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userFollow")
    private Set<UserFollowEntity> userEntityFollow = new HashSet<>();



}
