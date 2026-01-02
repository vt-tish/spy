package com.spy.server.common.repository;

import com.spy.server.common.entity.BaseEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> {
    @NotNull
    T create(@NotNull T entity);

    @NotNull
    T update(@NotNull T entity);

    void deleteById(@NotNull UUID id);

    @Nullable
    T findById(@NotNull UUID id);

    @NotNull
    List<T> findAll(@PositiveOrZero int offset, @Positive int limit);

    @PositiveOrZero
    long count();

    boolean existsById(@NotNull UUID id);
}
