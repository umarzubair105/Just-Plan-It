package com.uz.justplan.resources;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"companyId", "loginId"}))
public class Resource extends AbstractPersistable<Long> {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String firstName;
    private String secondName;
    @Column(nullable = false)
    private String lastName;
    private String designation;

    @Column(name = "isLead", nullable = false)
    private boolean lead;
    @Column(nullable = false)
    private boolean individualCapacity;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = true)
    private Long leadResourceId;

    @Column(nullable = false)
    private boolean active;

    public Resource() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isLead() {
        return lead;
    }

    public void setLead(boolean lead) {
        this.lead = lead;
    }

    public boolean isIndividualCapacity() {
        return individualCapacity;
    }

    public void setIndividualCapacity(boolean individualCapacity) {
        this.individualCapacity = individualCapacity;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getLeadResourceId() {
        return leadResourceId;
    }

    public void setLeadResourceId(Long leadResourceId) {
        this.leadResourceId = leadResourceId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
