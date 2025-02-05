package com.uz.justplan.beans;

import jakarta.validation.constraints.NotBlank;

public class AddCompanyReq {
    @NotBlank(message = "It is required.")
    private String email;
    @NotBlank(message = "It is required.")
    private String resourceName;
    @NotBlank(message = "It is required.")
    private String name;
    @NotBlank(message = "It is required.")
    private String designation;
    @NotBlank(message = "It is required.")
    private Integer countryId;
    @NotBlank(message = "It is required.")
    private Long sampleCompanyId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Long getSampleCompanyId() {
        return sampleCompanyId;
    }

    public void setSampleCompanyId(Long sampleCompanyId) {
        this.sampleCompanyId = sampleCompanyId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
