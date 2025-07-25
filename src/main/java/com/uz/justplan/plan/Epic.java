package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.EpicStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
public class Epic extends Auditable {
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EpicStatus status = EpicStatus.OPEN;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private String details;
    @Column(nullable = true)
    private Long dependOnEpicId;
    @Column(nullable = true)
    private Long raisedByResourceId;
    @Column(nullable = true)
    private Long componentId;
    private LocalDate requiredBy;
    @Column(nullable = true)
    private Long priorityId;
    private String comments;
    private String risks;
    private double valueGain;
    @Column(nullable = false)
    private boolean active;


    @Column(nullable = true)
    private Long releaseId;
    @Column(nullable = false)
    private boolean forcefullyAdded;
    @Column(nullable = true)
    private LocalDate startDate;
    @Column(nullable = true)
    private LocalDate endDate;
    public Epic() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Long getRaisedByResourceId() {
        return raisedByResourceId;
    }

    public void setRaisedByResourceId(Long raisedByResourceId) {
        this.raisedByResourceId = raisedByResourceId;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }

    public double getValueGain() {
        return valueGain;
    }

    public void setValueGain(double valueGain) {
        this.valueGain = valueGain;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
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

    public EpicStatus getStatus() {
        return status;
    }

    public void setStatus(EpicStatus status) {
        this.status = status;
    }
}
