package com.example.startappdemo.repository.dsl;

import com.example.startappdemo.entity.UserFollowEntity;
import com.querydsl.core.Tuple;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;


public interface UserFollowDslRepository  extends BaseDslRepository<UserFollowEntity, UUID>{
    List<Tuple> findListUserFollow(UUID userId);


}
