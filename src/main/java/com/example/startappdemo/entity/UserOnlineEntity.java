package com.example.startappdemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;


// Test cho đăng nhập 1 nơi
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User_Online" )
public class UserOnlineEntity extends  BaseEntity{


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id" )
    private UserEntity user;

    @Column(name = "timeConnect")
    private Timestamp timeConnect;

    @Column( name = "timeDisconnect")
    private Timestamp timeDisconnect;

    @Column( name = "ip_address")
    private String ip;


}
