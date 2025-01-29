package com.uz.justplan.resources;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.DayOfWeek;

@Entity
public class CompanyWeekend extends AbstractPersistable<Long> implements Cloneable {

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    @Column(nullable = false)
    private boolean active;

    public CompanyWeekend() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CompanyWeekend cloned = (CompanyWeekend) super.clone();
        cloned.setId(null);
        return cloned;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
