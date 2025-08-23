package com.uz.justplan.services;

import com.uz.justplan.core.CompanyRepository;
import com.uz.justplan.core.ComponentRepository;
import com.uz.justplan.core.ProductRepository;
import com.uz.justplan.lookup.PriorityRepository;
import com.uz.justplan.plan.*;
import com.uz.justplan.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    ReleaseResourceRepository releaseResourceRepository;
    @Autowired
    EpicReleaseConcernRepository epicReleaseConcernRepository;
    @Autowired
    private CompanyRepository compRepo;
    @Autowired
    private ResourceRepository resourceRepo;
    @Autowired
    private ProductRepository productRepo;
    //@Autowired
    //private ResourceRoleRepository resourceRoleRepo;
    @Autowired
    private ProductResourceRepository prodResourceRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private ComponentRepository componentRepo;
    @Autowired
    private PriorityRepository priorityRepo;
    @Autowired
    private EpicRepository epicRepo;
    @Autowired
    private EpicEstimateRepository epicEstRepo;
    @Autowired
    private EpicAssignmentRepository epicAssignmentRepository;
    @Autowired
    private DesignationRepository designRepo;
    @Autowired
    private CompanyWeekendRepository companyWeekendRepository;
    @Autowired
    private CompanyWorkingHourRepository companyWorkingHourRepository;
    @Autowired
    private CompanyCalendarRepository companyCalRepository;
    @Autowired
    private ReleaseEpicHistoryRepository releaseEpicHistoryRepository;
    @Autowired
    private ReleaseRepository releaseRepository;
    @Autowired
    private ResourceLeaveRepository resourceLeavesRepository;
    @Autowired
    private ReleaseWorkingDayRepository releaseWorkingDayRepository;
    @Autowired
    private TimeLoggingRepository timeLoggingRepository;

    public Map<Long, List<EpicReleaseConcern>> getReleaseConcerns(Long releaseId) {
        List<EpicReleaseConcern> concerns = epicReleaseConcernRepository.findByReleaseIdAndActiveIsTrue(releaseId);
        if (concerns.isEmpty()) {
            return new HashMap<>();
        }
        final Map<Long, String> resourceNames = new HashMap<>();
        concerns.forEach(c -> {
            if (!resourceNames.containsKey(c.getAssignedToResourceId())) {
                resourceNames.put(c.getAssignedToResourceId(),
                        resourceRepo.findById(c.getAssignedToResourceId()).get().getName());
            }
            if (!resourceNames.containsKey(c.getCreatedById())) {
                resourceNames.put(c.getCreatedById(),
                        resourceRepo.findById(c.getCreatedById()).get().getName());
            }
            c.setAssignedToResourceName(resourceNames.get(c.getAssignedToResourceId()));
            c.setCreatedByName(resourceNames.get(c.getCreatedById()));
        });
        return concerns.stream().collect(Collectors.groupingBy(e -> e.getEpicId()));
    }

    public List<EpicReleaseConcern> getEpicReleaseConcerns(Long epicId, Long releaseId) {
        List<EpicReleaseConcern> concerns = epicReleaseConcernRepository.findByEpicIdAndReleaseIdAndActiveIsTrue(epicId, releaseId);
        if (concerns.isEmpty()) {
            return new ArrayList<>();
        }
        final Map<Long, String> resourceNames = new HashMap<>();
        concerns.forEach(c -> {
            if (!resourceNames.containsKey(c.getAssignedToResourceId())) {
                resourceNames.put(c.getAssignedToResourceId(),
                        resourceRepo.findById(c.getAssignedToResourceId()).get().getName());
            }
            if (!resourceNames.containsKey(c.getCreatedById())) {
                resourceNames.put(c.getCreatedById(),
                        resourceRepo.findById(c.getCreatedById()).get().getName());
            }
            c.setAssignedToResourceName(resourceNames.get(c.getAssignedToResourceId()));
            c.setCreatedByName(resourceNames.get(c.getCreatedById()));
        });
        return concerns;
    }

    public List<EpicReleaseConcern> getEpicConcerns(Long epicId) {
        List<EpicReleaseConcern> concerns = epicReleaseConcernRepository.findByEpicIdAndActiveIsTrue(epicId);
        if (concerns.isEmpty()) {
            return new ArrayList<>();
        }
        final Map<Long, String> resourceNames = new HashMap<>();
        concerns.forEach(c -> {
            if (!resourceNames.containsKey(c.getAssignedToResourceId())) {
                resourceNames.put(c.getAssignedToResourceId(),
                        resourceRepo.findById(c.getAssignedToResourceId()).get().getName());
            }
            if (!resourceNames.containsKey(c.getCreatedById())) {
                resourceNames.put(c.getCreatedById(),
                        resourceRepo.findById(c.getCreatedById()).get().getName());
            }
            c.setAssignedToResourceName(resourceNames.get(c.getAssignedToResourceId()));
            c.setCreatedByName(resourceNames.get(c.getCreatedById()));
        });
        return concerns;
    }

}
