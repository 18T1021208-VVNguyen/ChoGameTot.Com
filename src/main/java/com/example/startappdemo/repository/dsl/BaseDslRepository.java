package com.example.startappdemo.repository.dsl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDslRepository<T, ID> extends JpaRepository<T, ID> {
    T findByIdMandatory(ID id) throws IllegalArgumentException;
    void clear();
    void detach(T entity);
}
