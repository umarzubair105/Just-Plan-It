package com.uz.justplan.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uz.justplan.plan.EpicAssignment;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EpicAssignmentBean {
    Long id;
    double hours;
    Long roleId;
    String roleName;
    Long resourceId;
    String resourceName;
    Boolean active;
    Long epicId;

    public EpicAssignmentBean(EpicAssignment ee, String role, String resource) {
        this.id = ee.getId();
        this.active = ee.isActive();
        this.epicId = ee.getEpicId();
        this.resourceId = ee.getResourceId();
        this.hours = ee.getHours();
        this.roleId = ee.getRoleId();
        this.roleName = role;
        this.resourceName = resource;
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


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
