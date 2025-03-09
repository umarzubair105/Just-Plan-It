package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"epicId", "resourceId", "roleId"}))
public class EpicAssignment extends Auditable {
    @Column(nullable = false)
    private double hours;
    @Column(nullable = false)
    private Long roleId;
    @Column(nullable = false)
    private Long epicId;
    @Column(nullable = false)
    private boolean changedManually;
    @Column(nullable = false)
    private Long resourceId;
    @Column(nullable = false)
    private boolean active;

    public EpicAssignment() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
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

    public boolean isChangedManually() {
        return changedManually;
    }

    public void setChangedManually(boolean changedManually) {
        this.changedManually = changedManually;
    }
}
