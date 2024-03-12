package com.example.startappdemo.repository.dsl.impl;

import com.example.startappdemo.entity.*;
import com.example.startappdemo.repository.dsl.BaseDslRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

public abstract class BaseDslRepositoryImpl<T,ID> extends SimpleJpaRepository<T, ID> implements BaseDslRepository<T, ID> {
    private final EntityManager em;
    protected final JPAQueryFactory queryFactory;
    protected final QUserFollowEntity qUserFollowEntity = QUserFollowEntity.userFollowEntity;
    protected final QGroupChatEntity qGroupChatEntity = QGroupChatEntity.groupChatEntity;
    protected final QUserOnlineEntity qUserOnlineEntity = QUserOnlineEntity.userOnlineEntity;

    protected final QUserEntity qUserEntity = QUserEntity.userEntity1;

    protected final QSenderReceiverEntity qSenderReceiverEntity = QSenderReceiverEntity.senderReceiverEntity;
    public BaseDslRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.em = entityManager;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public T findByIdMandatory(ID id) throws IllegalArgumentException {
        return findById(id)
                .orElseThrow(()->new IllegalArgumentException("entity not found with id "+id));
    }

    @Override
    public void clear() {
        em.clear();
    }

    @Override
    public void detach(T entity) {
        em.detach(entity);
    }
}
