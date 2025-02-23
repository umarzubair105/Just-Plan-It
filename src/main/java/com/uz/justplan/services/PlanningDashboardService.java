package com.uz.justplan.services;

import com.uz.justplan.beans.response.EpicBean;
import com.uz.justplan.beans.response.EpicEstimateBean;
import com.uz.justplan.core.Component;
import com.uz.justplan.core.ComponentRepository;
import com.uz.justplan.lookup.Priority;
import com.uz.justplan.lookup.PriorityRepository;
import com.uz.justplan.plan.Epic;
import com.uz.justplan.plan.EpicEstimate;
import com.uz.justplan.plan.EpicEstimateRepository;
import com.uz.justplan.plan.EpicRepository;
import com.uz.justplan.resources.Resource;
import com.uz.justplan.resources.ResourceRepository;
import com.uz.justplan.resources.Role;
import com.uz.justplan.resources.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanningDashboardService {
    @Autowired
    EpicRepository epicRepository;
    @Autowired
    EpicEstimateRepository epicEstimateRepository;
    @Autowired
    PriorityRepository priorityRepository;
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    RoleRepository roleRepository;

    public List<EpicBean> getUnplannedEpics(long companyId, long productId) {
        List<EpicBean> beans = new ArrayList<>();
        List<Epic> unplannedEpics = epicRepository.findByProductIdAndReleaseIdIsNullAndActiveIsTrue(productId);
        Map<Long, Priority> priorityMap = priorityRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Priority::getId, m -> m));
        Map<Long, String> resourceMap = resourceRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Resource::getId, m -> m.getName()));
        Map<Long, String> roleMap = roleRepository.findByCompanyIdAndActive(companyId, true).stream()
                .collect(Collectors.toMap(Role::getId, m -> m.getName()));
        Map<Long, String> compMap = componentRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Component::getId, m -> m.getName()));
        Map<Long, List<EpicEstimate>> epicEstMap = epicEstimateRepository.findUnplannedEpicEstimatedByProductId(productId)
                .stream().collect(Collectors.groupingBy(e -> e.getEpicId()));


        unplannedEpics.parallelStream().forEach(e -> {
            Optional<String> depEpicCode = Optional.empty();
            if (e.getDependOnEpicId() != null) {
                depEpicCode = Optional.ofNullable(epicRepository.findById(e.getDependOnEpicId()).orElse(null)).map(Epic::getCode);
            }

            List<EpicEstimateBean> esList = null;
            if (epicEstMap.containsKey(e.getId())) {
                esList = epicEstMap.get(e.getId()).stream().map(ee ->
                                new EpicEstimateBean(ee, roleMap.get(ee.getRoleId()), resourceMap.get(ee.getResourceId())))
                        .collect(Collectors.toList());
            }

            EpicBean bean = new EpicBean(e, priorityMap.get(e.getPriorityId()),
                    depEpicCode.isPresent() ? depEpicCode.get() : null,
                    resourceMap.get(e.getRaisedByResourceId()),
                    compMap.get(e.getComponentId()),
                    esList);

            beans.add(bean);
        });
        return beans;
    }

}
