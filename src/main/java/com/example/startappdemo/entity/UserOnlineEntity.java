package com.example.startappdemo.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Date timeConnect;

    @Column( name = "timeDisconnect")
    private Date timeDisconnect;

    @Column( name = "ip_address")
    private String ip;


}
