package com.spy.server.common.repository;

import com.spy.server.common.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class AbstractBaseRepository<T extends BaseEntity> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<T> entity;

    protected AbstractBaseRepository(Class<T> entity) {
        this.entity = entity;
    }
}
