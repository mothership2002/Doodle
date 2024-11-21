package com.example.webflux.model.entity;

import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
public abstract class BaseUserEntityColumn extends BaseEntityColumn {

    /**
     * construct on create by user
     * @param createdAt
     * @param createdBy
     */
    public BaseUserEntityColumn(LocalDateTime createdAt, Long createdBy) {
        super(createdAt);
        this.createdBy = createdBy;
    }
    
    @Column(value = "CREATED_BY")
    protected Long createdBy;

    @Column(value = "UPDATED_BY")
    protected Long modifiedBy;

    public void update(Long modifiedBy) {
        super.update(LocalDateTime.now());
        this.modifiedBy = modifiedBy;
    }
}