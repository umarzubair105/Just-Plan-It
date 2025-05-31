package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.EpicLinkType;
import jakarta.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "idx_EpicLink", columnList = "epicId, linkType")
})
public class EpicLink extends Auditable {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EpicLinkType linkType;
    @Column(nullable = false)
    private Long epicId;
    @Column(nullable = false)
    private Long linkedEpicId;

    @Column(nullable = true)
    private String details;


    @Column(nullable = false)
    private boolean active;

    public EpicLink() {
    }

    public EpicLinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(EpicLinkType linkType) {
        this.linkType = linkType;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public Long getLinkedEpicId() {
        return linkedEpicId;
    }

    public void setLinkedEpicId(Long linkedEpicId) {
        this.linkedEpicId = linkedEpicId;
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
