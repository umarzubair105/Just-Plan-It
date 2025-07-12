package com.uz.justplan.services;

import com.uz.justplan.beans.CommonResp;
import com.uz.justplan.beans.ReleaseDetailBean;
import com.uz.justplan.beans.ScheduleEpic;
import com.uz.justplan.beans.response.EpicAssignmentBean;
import com.uz.justplan.beans.response.EpicBean;
import com.uz.justplan.beans.response.EpicEstimateBean;
import com.uz.justplan.core.Component;
import com.uz.justplan.core.ComponentRepository;
import com.uz.justplan.core.Product;
import com.uz.justplan.core.ProductRepository;
import com.uz.justplan.lookup.AssignmentStatus;
import com.uz.justplan.lookup.Priority;
import com.uz.justplan.lookup.PriorityRepository;
import com.uz.justplan.lookup.ReleaseStatusEnum;
import com.uz.justplan.plan.*;
import com.uz.justplan.resources.Resource;
import com.uz.justplan.resources.ResourceRepository;
import com.uz.justplan.resources.Role;
import com.uz.justplan.resources.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanningDashboardService {
    @Autowired
    ReleaseService releaseService;
    @Autowired
    EpicService epicService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EpicRepository epicRepository;
    @Autowired
    EpicEstimateRepository epicEstimateRepository;
    @Autowired
    EpicAssignmentRepository epicAssignmentRepository;
    @Autowired
    PriorityRepository priorityRepository;
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ReleaseRepository releaseRepository;
    @Autowired
    TimeLoggingRepository timeLoggingRepository;

    public EpicBean findEpicByCompanyIdAndCode(long companyId, String code) {
        List<Epic> epics = epicRepository.findByCompanyIdAndCode(companyId, code);
        Assert.notEmpty(epics, "There is not record found.");
        Epic e = epics.get(0);
        System.out.println("------------------------findEpicByCompanyIdAndCode:" + code);
        EpicBean bean = new EpicBean(e,
                priorityRepository.findById(e.getPriorityId()).get(),
                null,
                e.getRaisedByResourceId() != null ? resourceRepository.findById(e.getRaisedByResourceId()).get().getName() : null,
                e.getComponentId() != null ? componentRepository.findById(e.getComponentId()).get().getName() : null,
                null);
        return bean;
    }

    public EpicBean findEpicById(long epicId) {
        Epic e = epicRepository.findById(epicId).get();
        Assert.notNull(e, "There is not record found.");
        EpicBean bean = new EpicBean(e,
                priorityRepository.findById(e.getPriorityId()).get(),
                null,
                e.getRaisedByResourceId() != null ? resourceRepository.findById(e.getRaisedByResourceId()).get().getName() : null,
                e.getComponentId() != null ? componentRepository.findById(e.getComponentId()).get().getName() : null,
                null);
        return bean;
    }
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
                                new EpicEstimateBean(ee, roleMap.get(ee.getRoleId())))
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

    @Transactional

    public ScheduleEpic planEpic(long epicId) {
        final Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new RuntimeException("Epic not found"));
        List<Release> unplannedRelease = releaseRepository.findByProductIdAndStatusAndActiveIsTrueOrderByStartDateAsc(
                epic.getProductId(), ReleaseStatusEnum.UNPLANNED);
        Release newReleaseAddedNow = null;
        if (unplannedRelease.isEmpty()) {
            newReleaseAddedNow = releaseService.addReleases(epic.getProductId());
            unplannedRelease.add(newReleaseAddedNow);
//            unplannedRelease = releaseRepository.findByProductIdAndStatusAndActiveIsTrueOrderByStartDateAsc(
//                    epic.getProductId(), ReleaseStatusEnum.UNPLANNED);
        }
        ScheduleEpic scheduleEpic = epicService.scheduleAndAssignEpicFirstTime(epic, unplannedRelease);
        if (scheduleEpic == null) {
            boolean anyReleaseExistWithZeroAssignment = false;
            for (Release release : unplannedRelease) {
                if (epicRepository.countByReleaseIdAndActiveTrue(release.getId()) == 0) {
                    anyReleaseExistWithZeroAssignment = true;
                    scheduleEpic = epicService.scheduleAndAssignEpicForcefully(epic, release);
                    if (scheduleEpic != null) {
                        break;
                    }
                }
            }
            if (scheduleEpic == null && newReleaseAddedNow == null) {
                Assert.isTrue(!anyReleaseExistWithZeroAssignment, "It can not be planned m1. There is no matching release based on time and resources.");
                Release release = releaseService.addReleases(epic.getProductId());
                scheduleEpic = epicService.scheduleAndAssignEpicForcefully(epic, release);
            }
            Assert.notNull(scheduleEpic, "It can not be planned m2. There is no matching release based on time and resources.");
        }
        if (scheduleEpic.getReleaseToAddIn() != null) {
            epic.setReleaseId(scheduleEpic.getReleaseToAddIn().getId()); // TODO: Replace with actual release id
            epicRepository.save(epic);
            scheduleEpic.getAssignments().forEach(assignment -> {
                EpicAssignment epicAssignment = new EpicAssignment();
                epicAssignment.setEpicId(epic.getId());
                epicAssignment.setResourceId(assignment.getResourceId());
                epicAssignment.setRoleId(assignment.getRoleId());
                epicAssignment.setEstimate(assignment.getMinutes());
                epicAssignment.setActive(true);
                epicAssignment.setStatus(AssignmentStatus.OPEN);
                epicAssignmentRepository.save(epicAssignment);
            });
        }
        return scheduleEpic;
    }

    @Transactional
    public CommonResp unplanEpic(long epicId) {
        final Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new RuntimeException("Epic not found"));
        Assert.isTrue(epic.getReleaseId() != null, epic.getCode() + " is not part of any release.");
        epic.setReleaseId(null); // TODO: Replace with actual release id
        epicRepository.save(epic);
        epicAssignmentRepository.findAllByEpicIdAndActiveTrue(epicId).forEach(assignment -> {
            assignment.setActive(false);
            epicAssignmentRepository.save(assignment);
        });
        CommonResp resp = new CommonResp();
        resp.setMessage(epic.getCode() + " is removed from release now.");
        return resp;
    }

    public ReleaseDetailBean findReleaseDetailByReleaseId(long releaseId) {
        ReleaseDetailBean releaseBean = new ReleaseDetailBean();
        Release release = releaseRepository.findById(releaseId).orElseThrow(() -> new RuntimeException("Release not found"));
        long productId = release.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        long companyId = product.getCompanyId();
        List<Epic> epics = epicRepository.findByReleaseIdAndActiveIsTrue(releaseId);
        Map<Long, Priority> priorityMap = priorityRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Priority::getId, m -> m));
        Map<Long, String> resourceMap = resourceRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Resource::getId, Resource::getName));
        Map<Long, String> roleMap = roleRepository.findByCompanyIdAndActive(companyId, true).stream()
                .collect(Collectors.toMap(Role::getId, Role::getName));
        Map<Long, String> compMap = componentRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Component::getId, Component::getName));
        Map<Long, List<EpicAssignment>> epicAssignmentMap = epicAssignmentRepository.findAllByReleaseId(releaseId)
                .stream().collect(Collectors.groupingBy(EpicAssignment::getEpicId));
        List<EpicBean> beans = new ArrayList<>();
        epics.parallelStream().forEach(e -> {
            Optional<String> depEpicCode = Optional.empty();
            if (e.getDependOnEpicId() != null) {
                depEpicCode = Optional.ofNullable(epicRepository.findById(e.getDependOnEpicId()).orElse(null)).map(Epic::getCode);
            }
            List<EpicAssignmentBean> esList = null;
            if (epicAssignmentMap.containsKey(e.getId())) {
                esList = epicAssignmentMap.get(e.getId()).stream().map(ee ->
                                new EpicAssignmentBean(ee, roleMap.get(ee.getRoleId()),
                                        resourceMap.get(ee.getResourceId()),
                                        release.getStatus() != ReleaseStatusEnum.UNPLANNED && release.getStatus() != ReleaseStatusEnum.PLANNED ?
                                                timeLoggingRepository.findTotalMinutesByReleaseIdAndEpicIdAndResourceId(releaseId, e.getId(), ee.getResourceId()) :
                                                timeLoggingRepository.findTotalMinutesByEpicIdAndResourceId(e.getId(), ee.getResourceId())
                                ))
                        .collect(Collectors.toList());
            }

            EpicBean bean = new EpicBean(esList, e, priorityMap.get(e.getPriorityId()),
                    depEpicCode.isPresent() ? depEpicCode.get() : null,
                    resourceMap.get(e.getRaisedByResourceId()),
                    compMap.get(e.getComponentId()));

            beans.add(bean);
        });
        releaseBean.setEpics(beans);
        releaseBean.setRelease(release);
        releaseBean.setResourceCaps(releaseService.getResourceCapInRelease(releaseId));
        return releaseBean;
    }

    public List<ReleaseDetailBean> getUnplannedReleases(long productId) {
        return getReleases(productId, ReleaseStatusEnum.UNPLANNED);
    }

    public List<ReleaseDetailBean> getPlannedReleases(long productId) {
        return getReleases(productId, ReleaseStatusEnum.PLANNED);
    }

    public List<ReleaseDetailBean> getStartedReleases(long productId) {
        return getReleases(productId, ReleaseStatusEnum.STARTED);
    }

    public List<ReleaseDetailBean> getOldReleases(long productId) {
        List<ReleaseStatusEnum> statuses = new ArrayList<>();
        statuses.add(ReleaseStatusEnum.OVERDUE);
        statuses.add(ReleaseStatusEnum.COMPLETED);
        List<Release> releases = releaseRepository.findByProductIdAndStatusInAndActiveIsTrueOrderByStartDateAsc(productId,
                statuses);
        List<ReleaseDetailBean> details = new ArrayList<>();
        releases.forEach(release -> {
            ReleaseDetailBean releaseBean = new ReleaseDetailBean();
            releaseBean.setRelease(release);
            details.add(releaseBean);
        });
        return details;
    }

    private List<ReleaseDetailBean> getReleases(long productId, ReleaseStatusEnum status) {
        List<Release> releases = releaseRepository.findByProductIdAndStatusAndActiveIsTrueOrderByStartDateAsc(productId,
                status);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        long companyId = product.getCompanyId();
        Map<Long, Priority> priorityMap = priorityRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Priority::getId, m -> m));
        Map<Long, String> resourceMap = resourceRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Resource::getId, Resource::getName));
        Map<Long, String> roleMap = roleRepository.findByCompanyIdAndActive(companyId, true).stream()
                .collect(Collectors.toMap(Role::getId, Role::getName));
        Map<Long, String> compMap = componentRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Component::getId, Component::getName));
        List<ReleaseDetailBean> details = new ArrayList<>();
        releases.forEach(release -> {
            ReleaseDetailBean releaseBean = new ReleaseDetailBean();
            releaseBean.setRelease(release);
            details.add(releaseBean);
        });
        details.parallelStream().forEach(detail -> {
            Release release = detail.getRelease();
            long releaseId = release.getId();
            List<Epic> epics = epicRepository.findByReleaseIdAndActiveIsTrue(releaseId);
            Map<Long, List<EpicAssignment>> epicAssignmentMap = epicAssignmentRepository.findAllByReleaseId(releaseId)
                    .stream().collect(Collectors.groupingBy(EpicAssignment::getEpicId));
            List<EpicBean> beans = new ArrayList<>();
            epics.parallelStream().forEach(e -> {
                Optional<String> depEpicCode = Optional.empty();
                if (e.getDependOnEpicId() != null) {
                    depEpicCode = Optional.ofNullable(epicRepository.findById(e.getDependOnEpicId()).orElse(null)).map(Epic::getCode);
                }
                List<EpicAssignmentBean> esList = null;
                if (epicAssignmentMap.containsKey(e.getId())) {
                    esList = epicAssignmentMap.get(e.getId()).stream().map(ee ->
                                    new EpicAssignmentBean(ee, roleMap.get(ee.getRoleId()),
                                            resourceMap.get(ee.getResourceId()),
                                            release.getStatus() != ReleaseStatusEnum.UNPLANNED && release.getStatus() != ReleaseStatusEnum.PLANNED ?
                                                    timeLoggingRepository.findTotalMinutesByReleaseIdAndEpicIdAndResourceId(releaseId, e.getId(), ee.getResourceId()) :
                                                    timeLoggingRepository.findTotalMinutesByEpicIdAndResourceId(e.getId(), ee.getResourceId())
                                    ))
                            .collect(Collectors.toList());
                }

                EpicBean bean = new EpicBean(esList, e, priorityMap.get(e.getPriorityId()),
                        depEpicCode.isPresent() ? depEpicCode.get() : null,
                        resourceMap.get(e.getRaisedByResourceId()),
                        compMap.get(e.getComponentId()));

                beans.add(bean);
            });
            detail.setEpics(beans);
            detail.setResourceCaps(releaseService.getResourceCapInRelease(releaseId));
        });
        return details;
    }
}
