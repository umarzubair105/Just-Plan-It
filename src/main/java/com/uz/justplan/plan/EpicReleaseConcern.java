package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.ConcernStatus;
import jakarta.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "idx_EpicReleaseConcern_epicId", columnList = "epicId"),
        @Index(name = "idx_EpicReleaseConcern_releaseId", columnList = "releaseId"),
        @Index(name = "idx_EpicReleaseConcern_assignedToResourceId", columnList = "assignedToResourceId"),
        @Index(name = "idx_EpicReleaseConcern_createdById", columnList = "createdById")
})
public class EpicReleaseConcern extends Auditable {
    @Column(nullable = false)
    private Long epicId;
    @Column(nullable = false)
    private Long releaseId;
    @Column(nullable = false)
    private Long assignedToResourceId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ConcernStatus status;
    @Column(nullable = false)
    private boolean active;

    @Transient
    private String assignedToResourceName;
    @Transient
    private String createdByName;

    public EpicReleaseConcern() {
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public Long getAssignedToResourceId() {
        return assignedToResourceId;
    }

    public void setAssignedToResourceId(Long assignedToResourceId) {
        this.assignedToResourceId = assignedToResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ConcernStatus getStatus() {
        return status;
    }

    public void setStatus(ConcernStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAssignedToResourceName() {
        return assignedToResourceName;
    }

    public void setAssignedToResourceName(String assignedToResourceName) {
        this.assignedToResourceName = assignedToResourceName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
}
