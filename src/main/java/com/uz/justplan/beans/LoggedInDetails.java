package com.uz.justplan.beans;

import com.uz.justplan.beans.projections.BasicProjection;

import java.util.List;

public class LoggedInDetails {
    private BasicProjection company;
    private List<BasicProjection> products;

    public BasicProjection getCompany() {
        return company;
    }

    public void setCompany(BasicProjection company) {
        this.company = company;
    }

    public List<BasicProjection> getProducts() {
        return products;
    }

    public void setProducts(List<BasicProjection> products) {
        this.products = products;
    }
}
