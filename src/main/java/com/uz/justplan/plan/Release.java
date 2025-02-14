package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Releases", uniqueConstraints = @UniqueConstraint(columnNames = {"productId", "name", "version"}))
public class Release extends Auditable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int version;
    @Column(nullable = false)
    private int workingDays;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer releaseStatusId;
    @Column(nullable = false)
    private boolean active;

    @Transient
    private List<ReleaseWorkingDay> workingDaysList;
    public Release() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getReleaseStatusId() {
        return releaseStatusId;
    }

    public void setReleaseStatusId(Integer releaseStatusId) {
        this.releaseStatusId = releaseStatusId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public List<ReleaseWorkingDay> getWorkingDaysList() {
        return workingDaysList;
    }

    public void setWorkingDaysList(List<ReleaseWorkingDay> workingDaysList) {
        this.workingDaysList = workingDaysList;
    }
}
