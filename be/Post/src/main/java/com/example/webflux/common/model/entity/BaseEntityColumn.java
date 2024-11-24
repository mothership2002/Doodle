package com.example.webflux.common.model.entity;

import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public abstract class BaseEntityColumn {

    public BaseEntityColumn(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BaseEntityColumn(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(value = "CREATED_AT")
    protected LocalDateTime createdAt;

    @Column(value = "UPDATED_AT")
    protected LocalDateTime updatedAt;

    @SuppressWarnings("unused")
    public String getCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    @SuppressWarnings("unused")
    public String getUpdatedAt() {
        return ObjectUtils.isEmpty(updatedAt) ? null : updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    public void update(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
