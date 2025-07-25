package com.uz.justplan.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uz.justplan.lookup.EpicStatus;
import com.uz.justplan.lookup.Priority;
import com.uz.justplan.plan.Epic;
import com.uz.justplan.plan.Release;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EpicBean {
    Long id;
    String code, title, details;
    EpicStatus status;
    Long dependOnEpicId;
    LocalDate requiredBy;
    Long priorityId;
    Long releaseId;
    Long componentId;
    Long productId;
    String risks;
    boolean forcefullyAdded;

    LocalDate startDate;
    LocalDate endDate;
    LocalDateTime createdDate, updatedDate;
    Release release;
    private Long createdById, updatedById;
    double valueGain;
    String dependOnEpicCode;
    private String createdByName, updatedByName;
    String raisedByResourceName;
    String priorityName;
    String componentName;
    int priorityLevel = 100;
    boolean active;

    List<EpicEstimateBean> estimates;
    List<EpicAssignmentBean> assignments;

    public EpicBean(Epic epic, Priority p, String depEpicCode, String raisedByResource, String component,
                    List<EpicEstimateBean> estimates) {
        this.id = epic.getId();
        this.productId = epic.getProductId();
        this.releaseId = epic.getReleaseId();
        this.code = epic.getCode();
        this.status = epic.getStatus();
        this.active = epic.isActive();
        this.title = epic.getTitle();
        this.details = epic.getDetails();
        this.dependOnEpicId = epic.getDependOnEpicId();
        this.requiredBy = epic.getRequiredBy();
        this.risks = epic.getRisks();
        this.forcefullyAdded = epic.isForcefullyAdded();
        this.startDate = epic.getStartDate();
        this.endDate = epic.getEndDate();
        this.componentId = epic.getComponentId();
        this.createdById = epic.getCreatedById();
        this.updatedById = epic.getUpdatedById();
        this.createdDate = epic.getCreatedDate();
        this.updatedDate = epic.getUpdatedDate();
        this.componentName = component;
        if (p != null) {
            this.priorityId = p.getId();
            this.priorityName = p.getName();
            this.priorityLevel = p.getPriorityLevel();
        }
        this.dependOnEpicCode = depEpicCode;
        this.raisedByResourceName = raisedByResource;
        this.estimates = estimates == null ? new ArrayList<>() : estimates;
    }

    public EpicBean(List<EpicAssignmentBean> assignments, Epic epic, Priority p, String depEpicCode, String raisedByResource, String component) {
        this(epic, p, depEpicCode, raisedByResource, component, null);
        this.assignments = assignments == null ? new ArrayList<>() : assignments;
    }

    public List<EpicAssignmentBean> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<EpicAssignmentBean> assignments) {
        this.assignments = assignments;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getDependOnEpicId() {
        return dependOnEpicId;
    }

    public void setDependOnEpicId(Long dependOnEpicId) {
        this.dependOnEpicId = dependOnEpicId;
    }

    public LocalDate getRequiredBy() {
        return requiredBy;
    }

    public void setRequiredBy(LocalDate requiredBy) {
        this.requiredBy = requiredBy;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }

    public boolean isForcefullyAdded() {
        return forcefullyAdded;
    }

    public void setForcefullyAdded(boolean forcefullyAdded) {
        this.forcefullyAdded = forcefullyAdded;
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

    public double getValueGain() {
        return valueGain;
    }

    public void setValueGain(double valueGain) {
        this.valueGain = valueGain;
    }

    public String getDependOnEpicCode() {
        return dependOnEpicCode;
    }

    public void setDependOnEpicCode(String dependOnEpicCode) {
        this.dependOnEpicCode = dependOnEpicCode;
    }

    public String getRaisedByResourceName() {
        return raisedByResourceName;
    }

    public void setRaisedByResourceName(String raisedByResourceName) {
        this.raisedByResourceName = raisedByResourceName;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public List<EpicEstimateBean> getEstimates() {
        return estimates;
    }

    public void setEstimates(List<EpicEstimateBean> estimates) {
        this.estimates = estimates;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public EpicStatus getStatus() {
        return status;
    }

    public void setStatus(EpicStatus status) {
        this.status = status;
    }
}
