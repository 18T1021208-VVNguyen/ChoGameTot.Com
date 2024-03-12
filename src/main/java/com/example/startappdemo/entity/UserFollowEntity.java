package com.example.startappdemo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_follow",  uniqueConstraints = { //
        @UniqueConstraint(name = "USER_Follow", columnNames = { "user_id", "user_follow_id" })

})
public class UserFollowEntity extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_follow_id", nullable = false)
    private UserEntity userFollow;
}
