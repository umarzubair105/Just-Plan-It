package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.AssignmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"epicId", "resourceId", "roleId", "releaseId", "active"}),
        indexes = {
                @Index(name = "idx_epic_assignment_resource", columnList = "resourceId"),
                @Index(name = "idx_epic_assignment_release", columnList = "releaseId")
        })
public class EpicAssignment extends Auditable {
    @Column(nullable = false)
    private int estimate;
    @Column(nullable = false)
    private Long roleId;
    @Column(nullable = false)
    private Long epicId;
    @Column(nullable = false)
    private boolean changedManually;
    @Column(nullable = false)
    private Long resourceId;
    @Column(nullable = false)
    private Long releaseId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    @Column(nullable = true)
    private LocalDate expectedDeliveryDate;
    @Column(nullable = false)
    private boolean active;

    public EpicAssignment() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
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

    public boolean isChangedManually() {
        return changedManually;
    }

    public void setChangedManually(boolean changedManually) {
        this.changedManually = changedManually;
    }

    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
}
