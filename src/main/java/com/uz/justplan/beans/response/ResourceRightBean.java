package com.uz.justplan.beans.response;

import com.uz.justplan.core.Product;

import java.util.List;

public class ResourceRightBean {
    private long resourceId;
    private List<Product> products;
    private List<Long> teamResourceIds;
    private boolean globalManager;
    private boolean globalHr;
    private boolean globalAdmin;
    private boolean productManager;
    private boolean productHr;
    private boolean productAdmin;
    private Long leadId;
    private Long globalRoleId;
    private Long productRoleId;

    public List<Long> getTeamResourceIds() {
        return teamResourceIds;
    }

    public void setTeamResourceIds(List<Long> teamResourceIds) {
        this.teamResourceIds = teamResourceIds;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isGlobalManager() {
        return globalManager;
    }

    public void setGlobalManager(boolean globalManager) {
        this.globalManager = globalManager;
    }

    public boolean isGlobalHr() {
        return globalHr;
    }

    public void setGlobalHr(boolean globalHr) {
        this.globalHr = globalHr;
    }

    public boolean isGlobalAdmin() {
        return globalAdmin;
    }

    public void setGlobalAdmin(boolean globalAdmin) {
        this.globalAdmin = globalAdmin;
    }

    public boolean isProductManager() {
        return productManager;
    }

    public void setProductManager(boolean productManager) {
        this.productManager = productManager;
    }

    public boolean isProductHr() {
        return productHr;
    }

    public void setProductHr(boolean productHr) {
        this.productHr = productHr;
    }

    public boolean isProductAdmin() {
        return productAdmin;
    }

    public void setProductAdmin(boolean productAdmin) {
        this.productAdmin = productAdmin;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public Long getGlobalRoleId() {
        return globalRoleId;
    }

    public void setGlobalRoleId(Long globalRoleId) {
        this.globalRoleId = globalRoleId;
    }

    public Long getProductRoleId() {
        return productRoleId;
    }

    public void setProductRoleId(Long productRoleId) {
        this.productRoleId = productRoleId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }
}
