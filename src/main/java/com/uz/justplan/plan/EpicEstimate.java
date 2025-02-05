package com.uz.justplan.plan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"epicId", "roleId"}))
public class EpicEstimate extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private int resources;
    @Column(nullable = false)
    private double hours;
    @Column(nullable = false)
    private Long roleId;
    @Column(nullable = false)
    private Long epicId;

    @Column(nullable = true)
    private Long resourceId;
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

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
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
