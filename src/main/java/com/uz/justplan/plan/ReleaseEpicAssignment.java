package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class ReleaseEpicAssignment extends Auditable {
    @Column(nullable = false)
    private double days;
    private LocalDate startDate;
    private LocalDate endDate;

    @Column(nullable = false)
    private Long releaseId;

    @Column(nullable = false)
    private Long epicId;

    @Column(nullable = true)
    private Long resourceId;
    @Column(nullable = false)
    private boolean active;

    public ReleaseEpicAssignment() {
    }

    public double getDays() {
        return days;
    }

    public void setDays(double days) {
        this.days = days;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
