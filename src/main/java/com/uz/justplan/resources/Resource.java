package com.uz.justplan.resources;

import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.ResourceStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"companyId", "email"}))
public class Resource extends Auditable {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    private String mobileNumber;
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private Long countryId;

    @Column(nullable = true)
    private Long designationId;

    @Column(nullable = true)
    private Long roleId;

    @Column(name = "isLead", nullable = false)
    private boolean lead;
    @Column(nullable = false)
    private boolean individualCapacity;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = true)
    private LocalDate lastWorkingDate;

    @Column(nullable = true)
    private Long leadResourceId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceStatus status;
    @Column(nullable = false)
    private boolean active;

    public Resource() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Long designationId) {
        this.designationId = designationId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLastWorkingDate() {
        return lastWorkingDate;
    }

    public void setLastWorkingDate(LocalDate lastWorkingDate) {
        this.lastWorkingDate = lastWorkingDate;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
