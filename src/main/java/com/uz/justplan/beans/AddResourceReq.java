package com.uz.justplan.beans;

import jakarta.validation.constraints.NotBlank;

public class AddResourceReq {

    @NotBlank(message = "It is required.")
    private Long companyId;

    @NotBlank(message = "It is required.")
    private String email;
    @NotBlank(message = "It is required.")
    private Long roleId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
