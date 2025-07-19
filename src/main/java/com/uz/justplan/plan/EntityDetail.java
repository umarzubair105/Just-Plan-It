package com.uz.justplan.plan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.EntityDetailType;
import com.uz.justplan.lookup.EntityType;
import jakarta.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "idx_EntityDetail", columnList = "entityType, entityId, detailType")
})

public class EntityDetail extends Auditable {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityDetailType detailType;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityType entityType;
    @Column(nullable = false)
    private Long entityId;
    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private String details;
    private String link;
    @Column(nullable = false)
    private boolean active;

    @Transient
    private String createdByName;

    public EntityDetail() {
    }

    public EntityDetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(EntityDetailType detailType) {
        this.detailType = detailType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @JsonIgnore
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
}
