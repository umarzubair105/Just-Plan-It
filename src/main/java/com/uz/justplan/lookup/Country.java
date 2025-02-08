package com.uz.justplan.lookup;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Country extends Auditable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String countryCode;

    @Column(nullable = false)
    private String phoneCode;
    @Column(nullable = false)
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}
