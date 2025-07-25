package com.uz.justplan.controllers;

import com.uz.justplan.beans.CommonResp;
import com.uz.justplan.beans.ReleaseDetailBean;
import com.uz.justplan.beans.ScheduleEpic;
import com.uz.justplan.beans.response.EpicBean;
import com.uz.justplan.services.PlanningDashboardService;
import com.uz.justplan.services.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/planning-dashboard")
public class PlanningDashboardController {
    @Autowired
    private PlanningDashboardService service;
    @Autowired
    private ReleaseService releaseService;

    @GetMapping("/findUnplannedEpics")
    public List<EpicBean> getUnplannedEpics(@RequestParam long companyId, @RequestParam long productId) {
        return service.getUnplannedEpics(companyId, productId);
    }

    @PostMapping("/planEpic")
    public ScheduleEpic planEpic(@RequestParam long epicId) {
        return service.planEpic(epicId);
    }

    @PostMapping("/unplanEpic")
    public CommonResp unplanEpic(@RequestParam long epicId) {
        return service.unplanEpic(epicId);
    }

    @GetMapping("/findReleaseDetailByReleaseId")
    public ReleaseDetailBean findReleaseDetailByReleaseId(@RequestParam long releaseId) {
        return service.findReleaseDetailByReleaseId(releaseId);
    }

    @GetMapping("/findUnplannedReleasesByProductId")
    public List<ReleaseDetailBean> findUnplannedReleasesByProductId(@RequestParam long productId) {
        return service.getUnplannedReleases(productId);
    }

    @GetMapping("/findPlannedReleasesByProductId")
    public List<ReleaseDetailBean> findPlannedReleasesByProductId(@RequestParam long productId) {
        return service.getPlannedReleases(productId);
    }

    @GetMapping("/findStartedReleasesByProductId")
    public List<ReleaseDetailBean> findStartedReleasesByProductId(@RequestParam long productId) {
        return service.getStartedReleases(productId);
    }

    @GetMapping("/findOldReleasesByProductId")
    public List<ReleaseDetailBean> findOldReleasesByProductId(@RequestParam long productId) {
        return service.getOldReleases(productId);
    }

    @GetMapping("/findResourceDashboard")
    public List<ReleaseDetailBean> findResourceDashboard(@RequestParam long resourceId) {
        return service.getResourceDashboard(resourceId);
    }

    @PostMapping("/startRelease")
    public CommonResp startRelease(@RequestParam long releaseId) {
        return releaseService.startRelease(releaseId);
    }

    @PostMapping("/completeRelease")
    public CommonResp completeRelease(@RequestParam long releaseId) {
        return releaseService.completeRelease(releaseId);
    }
}
