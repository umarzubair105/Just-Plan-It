package com.uz.justplan.resources;

import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.RoleEnum;
import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"companyId", "name"}))
public class Role extends Auditable implements Cloneable {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum code;
    @Column(nullable = false)
    private boolean required;
    @Column(nullable = false)
    private boolean systemRole;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private boolean active;

    public Role() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Role cloned = (Role) super.clone();
        cloned.setId(null);
        return cloned;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public RoleEnum getCode() {
        return code;
    }

    public void setCode(RoleEnum code) {
        this.code = code;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isSystemRole() {
        return systemRole;
    }

    public void setSystemRole(boolean systemRole) {
        this.systemRole = systemRole;
    }
}
