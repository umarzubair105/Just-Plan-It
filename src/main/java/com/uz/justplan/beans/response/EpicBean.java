package com.uz.justplan.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uz.justplan.lookup.Priority;
import com.uz.justplan.plan.Epic;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EpicBean {
    Long id;
    String code, title, details;
    Long dependOnEpicId;
    LocalDate requiredBy;
    Long priorityId;
    Long componentId;
    String risks;
    boolean forcefullyAdded;

    LocalDate startDate;
    LocalDate endDate;
    double valueGain;
    String dependOnEpicCode;
    String raisedByResourceName;
    String priorityName;
    String componentName;
    int priorityLevel = 100;

    List<EpicEstimateBean> estimates;

    public EpicBean(Epic epic, Priority p, String depEpicCode, String raisedByResource, String component,
                    List<EpicEstimateBean> estimates) {
        this.id = epic.getId();
        this.code = epic.getCode();
        this.title = epic.getTitle();
        this.details = epic.getDetails();
        this.dependOnEpicId = epic.getDependOnEpicId();
        this.requiredBy = epic.getRequiredBy();
        this.risks = epic.getRisks();
        this.forcefullyAdded = epic.isForcefullyAdded();
        this.startDate = epic.getStartDate();
        this.endDate = epic.getEndDate();
        this.componentId = epic.getComponentId();
        this.componentName = component;
        if (p != null) {
            this.priorityId = p.getId();
            this.priorityName = p.getName();
            this.priorityLevel = p.getPriorityLevel();
        }
        this.dependOnEpicCode = depEpicCode;
        this.raisedByResourceName = raisedByResource;
        this.estimates = estimates;
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
}
