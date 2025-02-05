package com.uz.justplan.beans;

import jakarta.validation.constraints.NotBlank;

public class AddProductReq {

    @NotBlank(message = "It is required.")
    private Long companyId;
    private String name;
    private String emailProductManager;
    private String emailProductOwner;


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailProductManager() {
        return emailProductManager;
    }

    public void setEmailProductManager(String emailProductManager) {
        this.emailProductManager = emailProductManager;
    }

    public String getEmailProductOwner() {
        return emailProductOwner;
    }

    public void setEmailProductOwner(String emailProductOwner) {
        this.emailProductOwner = emailProductOwner;
    }

}
