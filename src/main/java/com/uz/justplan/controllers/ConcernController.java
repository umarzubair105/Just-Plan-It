package com.uz.justplan.controllers;

import com.uz.justplan.plan.EpicReleaseConcern;
import com.uz.justplan.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/epicReleaseConcerns")
public class ConcernController {
    private DashboardService service;

    @Autowired
    public ConcernController(final DashboardService service) {
        this.service = service;
    }

    @GetMapping("/getReleaseConcerns")
    public Map<Long, List<EpicReleaseConcern>> getReleaseConcerns(@RequestParam long releaseId) {
        return service.getReleaseConcerns(releaseId);
    }

    @GetMapping("/getEpicReleaseConcerns")
    public List<EpicReleaseConcern> getEpicReleaseConcerns(
            @RequestParam long epicId,
            @RequestParam long releaseId) {
        return service.getEpicReleaseConcerns(epicId, releaseId);
    }

    @GetMapping("/getEpicConcerns")
    public List<EpicReleaseConcern> getEpicConcerns(
            @RequestParam long epicId) {
        return service.getEpicConcerns(epicId);
    }
}
