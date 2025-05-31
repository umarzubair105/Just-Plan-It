package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.EpicDetailType;
import jakarta.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "idx_EpicDetail", columnList = "epicId, detailType")
})

public class EpicDetail extends Auditable {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EpicDetailType detailType;
    @Column(nullable = false)
    private Long epicId;
    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private String details;
    private String link;
    @Column(nullable = false)
    private boolean active;

    public EpicDetail() {
    }

    public EpicDetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(EpicDetailType detailType) {
        this.detailType = detailType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
