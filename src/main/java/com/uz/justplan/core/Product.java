package com.uz.justplan.core;

import com.uz.justplan.lookup.ReleaseIteration;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"companyId", "name"}))
public class Product extends Auditable {
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long productManagerId;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = true)
    private LocalDate endDate;

    private int otherActivitiesPercentTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReleaseIteration releaseIteration;

    @Column(nullable = false)
    private boolean active;


    public Product() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product other = (Product) obj;
        return getId() != null && getId().equals(other.getId()); // Compare based on ID
    }

    // ✅ Override hashCode
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReleaseIteration getReleaseIteration() {
        return releaseIteration;
    }

    public void setReleaseIteration(ReleaseIteration releaseIteration) {
        this.releaseIteration = releaseIteration;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductManagerId() {
        return productManagerId;
    }

    public void setProductManagerId(Long productManagerId) {
        this.productManagerId = productManagerId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOtherActivitiesPercentTime() {
        return otherActivitiesPercentTime;
    }

    public void setOtherActivitiesPercentTime(int otherActivitiesPercentTime) {
        this.otherActivitiesPercentTime = otherActivitiesPercentTime;
    }
}
