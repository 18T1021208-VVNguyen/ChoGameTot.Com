package com.example.startappdemo.repository.dsl.impl;

import com.example.startappdemo.entity.QUserFollowEntity;
import com.example.startappdemo.entity.UserFollowEntity;
import com.example.startappdemo.repository.dsl.UserFollowDslRepository;
import com.querydsl.core.Tuple;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserFollowDslRepositoryImpl  extends BaseDslRepositoryImpl<UserFollowEntity, UUID> implements UserFollowDslRepository {
    public UserFollowDslRepositoryImpl( EntityManager em) {
        super(UserFollowEntity.class, em);
    }

    @Override
    public List<Tuple> findListUserFollow(UUID userId) {
        List<Tuple> tuples = queryFactory
                .selectDistinct(qUserFollowEntity.id , qUserFollowEntity.userFollow.id
                ,qUserEntity.userName , qUserOnlineEntity.timeDisconnect )
                .from(qUserFollowEntity)
                .join(qUserEntity).on(qUserEntity.id.eq(qUserFollowEntity.userFollow.id))
                .leftJoin(qUserOnlineEntity).on(qUserEntity.id.eq(qUserOnlineEntity.user.id))
                .where(qUserFollowEntity.userEntity.id.eq(userId)).fetch();
        return tuples;
    }


}
