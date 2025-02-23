package com.uz.justplan.controllers;

import com.uz.justplan.beans.response.EpicBean;
import com.uz.justplan.services.PlanningDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/planning-dashboard")
public class PlanningDashboardController {
    @Autowired
    private PlanningDashboardService service;

    @GetMapping("/findUnplannedEpics")
    public List<EpicBean> getUnplannedEpics(@RequestParam long companyId, @RequestParam long productId) {
        return service.getUnplannedEpics(companyId, productId);
    }

}
