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
    private Long release;
    @Column(nullable = false)
    private LocalDate workingDate;
    @Column(nullable = false)
    private int minDate;
    @Column(nullable = false)
    private boolean active;

    public ReleaseWorkingDay() {
    }

    public Long getRelease() {
        return release;
    }

    public void setRelease(Long release) {
        this.release = release;
    }

    public LocalDate getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(LocalDate workingDate) {
        this.workingDate = workingDate;
    }

    public int getMinDate() {
        return minDate;
    }

    public void setMinDate(int minDate) {
        this.minDate = minDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
