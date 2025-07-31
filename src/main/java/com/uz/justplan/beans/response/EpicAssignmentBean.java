package com.uz.justplan.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uz.justplan.lookup.AssignmentStatus;
import com.uz.justplan.plan.EpicAssignment;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EpicAssignmentBean {
    Long id;
    int estimate;
    Long roleId;
    String roleName;
    Long resourceId;
    String resourceName;
    Boolean active;
    Long epicId;
    AssignmentStatus status;
    long minutesLogged;

    LocalDate expectedDeliveryDate;

    public EpicAssignmentBean(EpicAssignment ee, String role, String resource, Long minutesLogged) {
        this.id = ee.getId();
        this.active = ee.isActive();
        this.epicId = ee.getEpicId();
        this.resourceId = ee.getResourceId();
        this.estimate = ee.getEstimate();
        this.roleId = ee.getRoleId();
        this.roleName = role;
        this.resourceName = resource;
        this.status = ee.getStatus();
        this.expectedDeliveryDate = ee.getExpectedDeliveryDate();
        this.minutesLogged = minutesLogged == null ? 0 : minutesLogged.longValue();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
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


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    public long getMinutesLogged() {
        return minutesLogged;
    }

    public void setMinutesLogged(long minutesLogged) {
        this.minutesLogged = minutesLogged;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
