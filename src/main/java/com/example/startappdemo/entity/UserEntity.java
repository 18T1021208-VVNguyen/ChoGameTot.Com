package com.example.startappdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


//@Transactional
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private Set<UserRoleEntity> roleUser = new HashSet<>();



}
