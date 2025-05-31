package com.uz.justplan.core;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 🔹 Enable Auditing
public abstract class Auditable extends AbstractPersistable<Long> {

    @CreatedBy
    @Column(updatable = false)
    private Long createdById; // Stores the user who created the entity

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate; // Stores the date when entity was created

    @LastModifiedBy
    private Long updatedById; // Stores the user who last modified the entity

    @LastModifiedDate
    private LocalDateTime updatedDate; // Stores the date of last update

    public Long getCreatedById() {
        return createdById;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}