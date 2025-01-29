package com.uz.justplan.resources;

import com.uz.justplan.lookup.RoleEnum;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"companyId", "code"}))
public class Role extends AbstractPersistable<Long> implements Cloneable {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum code;
    @Column(nullable = false)
    private boolean taskAssignable;
    @Column(nullable = false)
    private boolean groupTask;

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

    public boolean isTaskAssignable() {
        return taskAssignable;
    }

    public void setTaskAssignable(boolean taskAssignable) {
        this.taskAssignable = taskAssignable;
    }

    public boolean isGroupTask() {
        return groupTask;
    }

    public void setGroupTask(boolean groupTask) {
        this.groupTask = groupTask;
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
}
