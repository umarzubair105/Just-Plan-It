package com.uz.justplan.plan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Entity
public class Epic extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Long raisedByResourceId;
    @Column(nullable = true)
    private Long componentId;
    private LocalDate requiredBy;
    @Column(nullable = true)
    private Integer priorityId;
    private String comments;
    private String risks;
    private double valueGain;
    @Column(nullable = false)
    private boolean planned;
    @Column(nullable = true)
    private Long completeByReleaseId;

    @Column(nullable = false)
    private boolean active;

    public Epic() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
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

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }

    public Long getCompleteByReleaseId() {
        return completeByReleaseId;
    }

    public void setCompleteByReleaseId(Long completeByReleaseId) {
        this.completeByReleaseId = completeByReleaseId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
