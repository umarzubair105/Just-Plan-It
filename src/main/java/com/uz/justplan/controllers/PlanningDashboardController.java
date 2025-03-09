package com.uz.justplan.controllers;

import com.uz.justplan.beans.ReleaseDetailBean;
import com.uz.justplan.beans.ScheduleEpic;
import com.uz.justplan.beans.response.EpicBean;
import com.uz.justplan.services.PlanningDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/planEpic")
    public ScheduleEpic planEpic(@RequestParam long epicId) {
        return service.planEpic(epicId);
    }

    @GetMapping("/findReleaseDetailByReleaseId")
    public ReleaseDetailBean findReleaseDetailByReleaseId(@RequestParam long releaseId) {
        return service.findReleaseDetailByReleaseId(releaseId);
    }

    @GetMapping("/findUnplannedReleasesByProductId")
    public List<ReleaseDetailBean> findUnplannedReleasesByProductId(@RequestParam long productId) {
        return service.getUnplannedReleases(productId);
    }
}
