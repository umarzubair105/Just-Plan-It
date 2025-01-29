package com.uz.justplan.plan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Entity
@Table(name = "Releases",
        uniqueConstraints = @UniqueConstraint(columnNames = {"productId", "name", "version"}))
public class Release extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String version;
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

    public Release() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
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
}
