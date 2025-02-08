package com.uz.justplan.resources;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class ResourceRole extends Auditable {
    @Column(nullable = false)
    private Long resourceId;

    @Column(nullable = false)
    private Long roleId;
    private int participationPercentTime;
    @Column(nullable = false)
    private boolean active;

    public ResourceRole() {
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public int getParticipationPercentTime() {
        return participationPercentTime;
    }

    public void setParticipationPercentTime(int participationPercentTime) {
        this.participationPercentTime = participationPercentTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
