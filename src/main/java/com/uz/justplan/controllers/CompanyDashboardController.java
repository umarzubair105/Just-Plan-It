package com.uz.justplan.controllers;

import com.uz.justplan.beans.*;
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

    @PostMapping("/add-product")
    public CommonResp addProduct(@RequestBody AddProductReq req) {
        return service.addProduct(req);
    }

    @PostMapping("/add-epics")
    public List<CommonResp> addEpics(@RequestBody List<AddEpicReq> list) {
        return service.addEpics(list);
    }

    @PostMapping("/add-resource-multiple")
    public List<CommonResp> addResourceMultiple(@RequestBody AddResourceReq req) {
        return service.addResourceSimple(req);
    }

    @PostMapping("/add-resources")
    public List<CommonResp> addResources(@RequestBody List<AddResourceReq> req) {
        return service.addResources(req);
    }

    @PostMapping("/add-resource")
    public CommonResp addResource(@RequestBody AddResourceReq req) {
        return service.addResource(req);
    }
    @PostMapping("/map-designation")
    public CommonResp mapDesignation(@RequestBody MapDesignationReq req) {
        return service.mapDesignation(req);
    }
}
