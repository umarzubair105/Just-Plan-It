package com.uz.justplan.resources;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"companyId", "name"}))
public class Designation extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = true)
    private Long roleId;

    @Column(nullable = false)
    private boolean active;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
