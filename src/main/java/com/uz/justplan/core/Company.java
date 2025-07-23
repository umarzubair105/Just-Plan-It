package com.uz.justplan.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Company extends Auditable {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private boolean sample;

    @Column(nullable = false)
    private Long countryId;
    @Column(nullable = false)
    private boolean active;

    public Company() {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return Objects.equals(getName(), company.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSample() {
        return sample;
    }

    public void setSample(boolean sample) {
        this.sample = sample;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
