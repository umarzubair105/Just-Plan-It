package com.uz.justplan.beans;

import com.uz.justplan.beans.projections.BasicProjection;
import com.uz.justplan.core.Company;

import java.util.List;

public class LoggedInDetails {
    private Company company;
    private List<BasicProjection> products;
    private String route;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<BasicProjection> getProducts() {
        return products;
    }

    public void setProducts(List<BasicProjection> products) {
        this.products = products;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
