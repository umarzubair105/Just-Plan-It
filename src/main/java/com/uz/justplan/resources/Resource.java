package com.uz.justplan.resources;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

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
    private int countryId;

    @Column(nullable = true)
    private Long designationId;

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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
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
}
