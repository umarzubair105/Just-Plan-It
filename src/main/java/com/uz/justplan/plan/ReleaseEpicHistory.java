package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"releaseId", "epicId"}))
public class ReleaseEpicHistory extends Auditable {
    private String comments;
    private String risks;

    @Column(nullable = false)
    private Long releaseId;

    @Column(nullable = false)
    private Long epicId;

    @Column(nullable = false)
    private boolean forcefullyAdded;

    @Column(nullable = false)
    private boolean active;

    public ReleaseEpicHistory() {
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public boolean isForcefullyAdded() {
        return forcefullyAdded;
    }

    public void setForcefullyAdded(boolean forcefullyAdded) {
        this.forcefullyAdded = forcefullyAdded;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
