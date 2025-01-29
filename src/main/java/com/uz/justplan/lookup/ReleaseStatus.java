package com.uz.justplan.lookup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ReleaseStatus extends AbstractPersistable<Integer> {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private ReleaseStatusEnum code;

    @Column(nullable = false)
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReleaseStatusEnum getCode() {
        return code;
    }

    public void setCode(ReleaseStatusEnum code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
