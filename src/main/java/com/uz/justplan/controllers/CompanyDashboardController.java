package com.uz.justplan.controllers;

import com.uz.justplan.beans.AddCompanyReq;
import com.uz.justplan.beans.AddResourceReq;
import com.uz.justplan.beans.CommonResp;
import com.uz.justplan.services.CompanyDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/company-dashboard")
public class CompanyDashboardController {
    private CompanyDashboardService service;

    @Autowired
    public CompanyDashboardController(final CompanyDashboardService service) {
        this.service = service;
    }

    @PostMapping("/add-company")
    public CommonResp addCompany(@RequestBody AddCompanyReq req) {
        return service.addCompany(req);
    }

    @PostMapping("/add-resource")
    public List<CommonResp> addResource(@RequestBody AddResourceReq req) {
        return service.addResource(req);
    }
}
