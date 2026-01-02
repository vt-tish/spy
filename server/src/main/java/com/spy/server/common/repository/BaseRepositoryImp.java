package com.spy.server.common.repository;

import com.spy.server.common.entity.BaseEntity;
import jakarta.persistence.NoResultException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
@NoRepositoryBean
public class BaseRepositoryImp<T extends BaseEntity>
        extends AbstractBaseRepository<T>
        implements BaseRepository<T> {
    protected BaseRepositoryImp(Class<T> entity) {
        super(entity);
    }

    @Override
    public T create(T entity) {
        try {
            entityManager.persist(entity);
            return entity;
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("[BASE REPOSITORY]: failed to create entity %s", this.entity.getSimpleName()),
                    ex
            );
        }
    }

    @Override
    public T update(T entity) {
        try {
            return entityManager.merge(entity);
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("[BASE REPOSITORY]: failed to update entity %s", this.entity.getSimpleName()),
                    ex
            );
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            T entity = entityManager.find(this.entity, id);
            if (entity == null) {
                throw new NoResultException(
                        String.format(
                                "[BASE REPOSITORY]: entity %s with id %s not found",
                                this.entity.getSimpleName(),
                                id
                        )
                );
            }
            entityManager.remove(entity);
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("[BASE REPOSITORY]: failed to delete entity %s with id %s",
                            this.entity.getSimpleName(), id),
                    ex
            );
        }
    }

    @Override
    public T findById(UUID id) {
        return this.entityManager.find(this.entity, id);
    }

    @Override
    public List<T> findAll(int offset, int limit) {
        try {
            return entityManager.createQuery("select e from " + this.entity.getSimpleName() +
                    " e order by e.createdAt desc", this.entity)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("[BASE REPOSITORY]: failed to find all %s (offset=%d, limit=%d)",
                            this.entity.getSimpleName(), offset, limit),
                    ex
            );
        }
    }

    @Override
    public long count() {
        try {
            return entityManager
                    .createQuery("select count(e) from " + entity.getSimpleName() + " e", Long.class)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("[BASE REPOSITORY]: failed to count entities %s", this.entity.getSimpleName()),
                    ex
            );
        }
    }

    @Override
    public boolean existsById(UUID id) {
        try {
            return entityManager
                    .createQuery("select count(e) from " + entity.getSimpleName() + " e where e.id = :id",
                            Long.class)
                    .setParameter("id", id)
                    .getSingleResult() > 0;
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("[BASE REPOSITORY]: failed to check existence of entity %s with id %s",
                            this.entity.getSimpleName(), id),
                    ex
            );
        }
    }
}
