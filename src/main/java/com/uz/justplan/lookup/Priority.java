package com.uz.justplan.lookup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Priority extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private String name;
    //@Column(nullable = false, unique = true)
    //private PriorityEnum code;
    @Column(nullable = false)
    private int priorityLevel;
    @Column(nullable = false)
    private long companyId;

    @Column(nullable = false)
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
