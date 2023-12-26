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
@Table(name = "User_Log_Time" )
public class UserLogTimeEntity extends  BaseEntity{


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id" )
    private UserEntity user;

    @Column( name = "ip_address", nullable = false)
    private String ip;

    @Column( name = "lasttime", nullable = false)
    private Date lastTime;


}
