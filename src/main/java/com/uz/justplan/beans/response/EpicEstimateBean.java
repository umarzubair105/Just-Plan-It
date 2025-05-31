package com.uz.justplan.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uz.justplan.plan.EpicEstimate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EpicEstimateBean {
    Long id;
    int resources;
    int estimate;
    Long roleId;
    String roleName;
    Boolean active;
    Long epicId;

    public EpicEstimateBean(EpicEstimate ee, String role) {
        this.id = ee.getId();
        this.active = ee.isActive();
        this.epicId = ee.getEpicId();
        this.resources = ee.getResources();
        this.estimate = ee.getEstimate();
        this.roleId = ee.getRoleId();
        this.roleName = role;
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


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
