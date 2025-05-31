package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"epicId", "roleId"}))
public class EpicEstimate extends Auditable {
    @Column(nullable = false)
    private int resources;
    @Column(nullable = false)
    private int estimate;
    @Column(nullable = false)
    private Long roleId;
    @Column(nullable = false)
    private Long epicId;
    @Column(nullable = false)
    private boolean active;

    public EpicEstimate() {
    }
    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
