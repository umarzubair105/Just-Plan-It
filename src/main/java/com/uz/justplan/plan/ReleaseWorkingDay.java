package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;

@Entity
@Table(name = "ReleaseWorkingDay", uniqueConstraints = @UniqueConstraint(columnNames = {"releaseId", "workingDate"}))
public class ReleaseWorkingDay extends Auditable {
    @Column(nullable = false)
    private Long releaseId;
    @Column(nullable = false)
    private LocalDate workingDate;
    @Column(nullable = false)
    private int minutes;
    @Column(nullable = false)
    private boolean active;

    public ReleaseWorkingDay() {
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public LocalDate getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(LocalDate workingDate) {
        this.workingDate = workingDate;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
