package com.uz.justplan.services;

import com.uz.justplan.beans.CommonResp;
import com.uz.justplan.beans.ReleaseDetailBean;
import com.uz.justplan.beans.ScheduleEpic;
import com.uz.justplan.beans.cal.ResourceCapInRelease;
import com.uz.justplan.beans.response.EpicAssignmentBean;
import com.uz.justplan.beans.response.EpicBean;
import com.uz.justplan.beans.response.EpicEstimateBean;
import com.uz.justplan.beans.response.RelatedEpicDetailBean;
import com.uz.justplan.core.Component;
import com.uz.justplan.core.ComponentRepository;
import com.uz.justplan.core.Product;
import com.uz.justplan.core.ProductRepository;
import com.uz.justplan.lookup.*;
import com.uz.justplan.plan.*;
import com.uz.justplan.resources.Resource;
import com.uz.justplan.resources.ResourceRepository;
import com.uz.justplan.resources.Role;
import com.uz.justplan.resources.RoleRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlanningDashboardService {
    private static final Log log = LogFactory.getLog(PlanningDashboardService.class);
    @Autowired
    ReleaseService releaseService;
    @Autowired
    EpicService epicService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EpicRepository epicRepository;
    @Autowired
    EpicLinkRepository epicLinkRepository;
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

    public List<EpicEstimateBean> findPossibleEpicEstimateByEpicId(long epicId) {
        Epic epic = epicRepository.findById(epicId).get();
        List<Role> roles = roleRepository.findActiveNonSystemOnlyRolesByProductId(epic.getProductId());
        List<EpicEstimate> estimates = epicEstimateRepository.findByEpicIdAndActiveIsTrue(epicId);
        final List<EpicEstimateBean> beans = new ArrayList<>();
        roles.sort(Comparator.comparing(Role::getName));

        roles.forEach(r -> {
            Optional<EpicEstimate> est = estimates.stream().filter(e -> Objects.equals(e.getRoleId(), r.getId())).findFirst();
            if (est.isPresent()) {
                beans.add(new EpicEstimateBean(est.get(), r.getName()));
            } else {
                EpicEstimate epes = new EpicEstimate();
                epes.setEstimate(0);
                epes.setActive(true);
                epes.setRoleId(r.getId());
                epes.setEpicId(epicId);
                epes.setResources(1);
                beans.add(new EpicEstimateBean(epes, r.getName()));
            }
        });
        return beans;
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
        bean.setCreatedByName(resourceRepository.findById(e.getCreatedById()).get().getName());
        bean.setUpdatedByName(resourceRepository.findById(e.getUpdatedById()).get().getName());
        if (e.getReleaseId() != null) {
            bean.setRelease(releaseRepository.findById(e.getReleaseId()).get());
        }
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
            populateEpicRelatedInfo(bean);

            beans.add(bean);
        });
        return beans;
    }

    @Transactional

    public ScheduleEpic planEpic(long epicIdToAssign) {
        final Epic epic = epicRepository.findById(epicIdToAssign).orElseThrow(() -> new RuntimeException("Epic not found"));
        List<Release> unplannedReleases = releaseRepository.findByProductIdAndStatusAndActiveIsTrueOrderByStartDateAsc(
                epic.getProductId(), ReleaseStatusEnum.UNPLANNED);
        if (epic.isReplicate()) {
            // check if replicated ticket is already part
            Assert.isTrue(!unplannedReleases.isEmpty(), "Template Deliverable can be planned only for existing unplanned" +
                    " releases. First plan deliverable for which Template is marked as 'No'.");
            ScheduleEpic scheduleEpic = null;
            for (Release release : unplannedReleases) {
                if (epicRepository.findAllByReplicatedEpicIdIdAndReleaseId(epic.getId(), release.getId()).isEmpty()) {
                    ScheduleEpic scheduleEpic2 = epicService.scheduleAndAssignEpicForcefully(epic, release);
                    if (scheduleEpic2 == null) {
                        continue;
                    }
                    scheduleEpic = scheduleEpic2;
                    if (scheduleEpic != null && scheduleEpic.getReleaseToAddIn() != null) {
                        final long releaseId = scheduleEpic.getReleaseToAddIn().getId();
                        Epic newEpic = replicateEpic(epic);
                        newEpic.setReleaseId(releaseId); // TODO: Replace with actual release id
                        epicRepository.save(newEpic);
                        final long epicId = newEpic.getId();

                        scheduleEpic.getAssignments().forEach(assignment -> {
                            EpicAssignment epicAssignment = new EpicAssignment();
                            epicAssignment.setEpicId(epicId);
                            epicAssignment.setResourceId(assignment.getResourceId());
                            epicAssignment.setRoleId(assignment.getRoleId());
                            epicAssignment.setReleaseId(releaseId);
                            epicAssignment.setEstimate(assignment.getMinutes());
                            epicAssignment.setActive(true);
                            epicAssignment.setStatus(AssignmentStatus.OPEN);
                            epicAssignmentRepository.save(epicAssignment);
                        });

                    }
                }
            }
            Assert.notNull(scheduleEpic, "Template Deliverable is already part of Unplanned release.");
            return scheduleEpic;
        }

        Release newReleaseAddedNow = null;
        if (unplannedReleases.isEmpty()) {
            newReleaseAddedNow = releaseService.addReleases(epic.getProductId());
            unplannedReleases.add(newReleaseAddedNow);
//            unplannedRelease = releaseRepository.findByProductIdAndStatusAndActiveIsTrueOrderByStartDateAsc(
//                    epic.getProductId(), ReleaseStatusEnum.UNPLANNED);
        }
        ScheduleEpic scheduleEpic = epicService.scheduleAndAssignEpicFirstTime(epic, unplannedReleases);
        //NEW
        if (scheduleEpic == null) {
            if (newReleaseAddedNow != null) {
                scheduleEpic = epicService.scheduleAndAssignEpicForcefully(epic, newReleaseAddedNow);
                Assert.notNull(scheduleEpic, "It can not be planned. There is no matching release based on time and resources." +
                        " Either Product team is not having resource with these roles or resource time allocation is not enough to cover in a release.");
            } else {
                boolean anyReleaseExistWithZeroAssignment = false;
                for (Release release : unplannedReleases) {
                    if (epicRepository.countByReleaseIdAndActiveTrue(release.getId()) == 0) {
                        anyReleaseExistWithZeroAssignment = true;
                        scheduleEpic = epicService.scheduleAndAssignEpicForcefully(epic, release);
                        if (scheduleEpic != null) {
                            break;
                        }
                    }
                }
                if (scheduleEpic == null) {
                    Assert.isTrue(!anyReleaseExistWithZeroAssignment, "It can not be planned. There is no matching release based on time and resources." +
                            " Either Product team is not having resource with these roles or resource time allocation is not enough to cover in a release.");
                    Release release = releaseService.addReleases(epic.getProductId());
                    scheduleEpic = epicService.scheduleAndAssignEpicForcefully(epic, release);
                    Assert.notNull(scheduleEpic, "It can not be planned because there is no matching release based on time and resources." +
                            " Either Product team is not having resource with these roles or resource time allocation is not enough.");
                }
            }
        }
        //OLD
        /*if (scheduleEpic == null) {
            boolean anyReleaseExistWithZeroAssignment = false;
            for (Release release : unplannedReleases) {
                if (epicRepository.countByReleaseIdAndActiveTrue(release.getId()) == 0) {
                    anyReleaseExistWithZeroAssignment = true;
                    scheduleEpic = epicService.scheduleAndAssignEpicForcefully(epic, release);
                    if (scheduleEpic != null) {
                        break;
                    }
                }
            }
            if (scheduleEpic == null && newReleaseAddedNow == null) {
                Assert.isTrue(!anyReleaseExistWithZeroAssignment, "It can not be planned. There is no matching release based on time and resources." +
                        " Either Product team is not having resource with these roles or resource time allocation is not enough to cover in a release.");
                Release release = releaseService.addReleases(epic.getProductId());
                scheduleEpic = epicService.scheduleAndAssignEpicForcefully(epic, release);
            }
            Assert.notNull(scheduleEpic, "It can not be planned because there is no matching release based on time and resources." +
                    " Either Product team is not having resource with these roles or resource time allocation is not enough.");
        }*/
        if (scheduleEpic.getReleaseToAddIn() != null) {
            final long releaseId = scheduleEpic.getReleaseToAddIn().getId();
            epic.setReleaseId(releaseId); // TODO: Replace with actual release id
            epicRepository.save(epic);
            final long epicId = epic.getId();
            scheduleEpic.getAssignments().forEach(assignment -> {
                EpicAssignment epicAssignment = new EpicAssignment();
                epicAssignment.setEpicId(epicId);
                epicAssignment.setResourceId(assignment.getResourceId());
                epicAssignment.setRoleId(assignment.getRoleId());
                epicAssignment.setReleaseId(releaseId);
                epicAssignment.setEstimate(assignment.getMinutes());
                epicAssignment.setActive(true);
                epicAssignment.setStatus(AssignmentStatus.OPEN);
                epicAssignmentRepository.save(epicAssignment);
            });
        }
        return scheduleEpic;
    }

    private Epic replicateEpic(Epic epic) {
        Epic newE = new Epic();
        newE.setTitle(epic.getTitle());
        newE.setReplicate(false);
        newE.setCode(getEpicCodeForNewEpic(epic.getProductId()));
        newE.setDetails(epic.getDetails());
        newE.setReplicatedById(epic.getId());
        newE.setDetails(epic.getDetails());
        newE.setStatus(EpicStatus.OPEN);
        newE.setRequiredBy(epic.getRequiredBy());
        newE.setActive(true);
        newE.setComponentId(epic.getComponentId());
        newE.setPriorityId(epic.getPriorityId());
        newE.setProductId(epic.getProductId());
        newE.setValueGain(epic.getValueGain());
        newE.setRisks(epic.getRisks());
        epicRepository.save(newE);
        return newE;
    }

    @Transactional
    public CommonResp unplanEpic(long epicId) {
        final Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new RuntimeException("Epic not found"));
        Assert.isTrue(epic.getReleaseId() != null, epic.getCode() + " is not part of any release.");

        epicAssignmentRepository.findAllByEpicIdAndReleaseIdAndActiveTrue(epicId, epic.getReleaseId()).forEach(assignment -> {
            assignment.setActive(false);
            epicAssignmentRepository.save(assignment);
        });
        epic.setReleaseId(null); // TODO: Replace with actual release id
        epicRepository.save(epic);
        CommonResp resp = new CommonResp();
        resp.setMessage(epic.getCode() + " is removed from release now.");
        return resp;
    }

    public ReleaseDetailBean findReleaseDetailByReleaseId(long releaseId) {
        ReleaseDetailBean releaseBean = new ReleaseDetailBean();
        Release release = releaseRepository.findById(releaseId).orElseThrow(() -> new RuntimeException("Release not found"));
        long productId = release.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        releaseBean.setProduct(product);
        long companyId = product.getCompanyId();
        List<Epic> epics = epicRepository.findByReleaseIdAndActiveIsTrueUsingHistory(releaseId);
        if (epics.isEmpty()) {
            epics = epicRepository.findByReleaseIdAndActiveIsTrue(releaseId);
        }
        Map<Long, Priority> priorityMap = priorityRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Priority::getId, m -> m));
        Map<Long, String> resourceMap = resourceRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Resource::getId, Resource::getName));
        Map<Long, String> roleMap = roleRepository.findByCompanyIdAndActive(companyId, true).stream()
                .collect(Collectors.toMap(Role::getId, Role::getName));
        Map<Long, String> compMap = componentRepository.findByCompanyIdAndActiveIsTrue(companyId).stream()
                .collect(Collectors.toMap(Component::getId, Component::getName));
        Map<Long, List<EpicAssignment>> epicAssignmentMap = epicAssignmentRepository.findAllByReleaseIdAndActiveTrue(releaseId)
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
            populateEpicRelatedInfo(bean);

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

    public List<ReleaseDetailBean> getResourceDashboard(long resourceId) {
        List<Release> releases = releaseRepository.findAllByResourceIdAndReleaseStatus(
                resourceId, ReleaseStatusEnum.STARTED);
        if (releases.isEmpty()) {
            return new ArrayList<>();
        }
        Resource res = resourceRepository.findById(resourceId).get();
        //Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        long companyId = res.getCompanyId();
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
            releaseBean.setProduct(productRepository.findById(release.getProductId()).get());
            releaseBean.setRelease(release);
            details.add(releaseBean);
        });
        details.parallelStream().forEach(detail -> {
            Release release = detail.getRelease();
            long releaseId = release.getId();
            List<Epic> epics = epicRepository.findAllByResourceIdAndReleaseId(resourceId, releaseId);
            Map<Long, List<EpicAssignment>> epicAssignmentMap = epicAssignmentRepository.findAllInvolvedByResourceIdAndReleaseId(
                            resourceId, releaseId)
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
                populateEpicRelatedInfo(bean);

                beans.add(bean);
            });
            detail.setEpics(beans);
            List<ResourceCapInRelease> caps = releaseService.getResourceCapInRelease(releaseId);
            detail.setResourceCaps(caps.stream().filter(c -> c.resourceId == resourceId).collect(Collectors.toList()));
        });
        return details;
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
            releaseBean.setProduct(productRepository.findById(release.getProductId()).get());
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
            releaseBean.setProduct(productRepository.findById(release.getProductId()).get());
            releaseBean.setRelease(release);
            details.add(releaseBean);
        });
        details.parallelStream().forEach(detail -> {
            Release release = detail.getRelease();
            long releaseId = release.getId();
            List<Epic> epics = epicRepository.findByReleaseIdAndActiveIsTrueUsingHistory(releaseId);
            if (epics.isEmpty()) {
                epics = epicRepository.findByReleaseIdAndActiveIsTrue(releaseId);
            }
            Map<Long, List<EpicAssignment>> epicAssignmentMap = epicAssignmentRepository.findAllByReleaseIdAndActiveTrue(releaseId)
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
                populateEpicRelatedInfo(bean);
                beans.add(bean);
            });
            detail.setEpics(beans);
            detail.setResourceCaps(releaseService.getResourceCapInRelease(releaseId));
        });
        return details;
    }

    private void populateEpicRelatedInfo(EpicBean epic) {
        List<EpicLink> links = epicLinkRepository.findByEpicIdAndActiveIsTrueOrderByCreatedDateAsc(epic.getId());
        epic.setRelatedTo(new ArrayList<>());
        epic.setDependsOn(new ArrayList<>());
        log.info("------------------------------");
        log.info("-----------------populateEpicRelatedInfo:" + epic.getCode() + ":" + links.size());
        if (!links.isEmpty()) {
            Map<Long, Epic> relatedEpics = epicRepository.findByRelatedEpicsByEpicId(epic.getId())
                    .stream().collect(Collectors.toMap(e -> e.getId(), e -> e));
            log.info("---------------------------populateEpicRelatedInfo:" + epic.getCode() + ":" + relatedEpics.size());
            Map<Long, Release> relatedReleases = releaseRepository.findOfRelatedEpicReleaseEpicId(epic.getId())
                    .stream().collect(Collectors.toMap(e -> e.getId(), e -> e));

            links.forEach(l -> {
                Epic related = relatedEpics.get(l.getLinkedEpicId());
                log.info("----------------------------------populateEpicRelatedInfo:" + epic.getCode() + ":" + related.getCode());
                if (related.getStatus() != EpicStatus.DELETED) {
                    LocalDate endDate = related.getReleaseId() != null && relatedReleases.containsKey(related.getReleaseId()) ?
                            relatedReleases.get(related.getReleaseId()).getEndDate() : null;
                    if (l.getLinkType() == EpicLinkType.RELATED_TO) {
                        epic.getRelatedTo().add(new RelatedEpicDetailBean(related, endDate
                        ));
                    } else if (l.getLinkType() == EpicLinkType.DEPEND_ON) {
                        epic.getDependsOn().add(new RelatedEpicDetailBean(related, endDate));
                    }
                }
            });
        }
    }

    private String getEpicCodeForNewEpic(long productId) {
        String prodCode = productRepository.findById(productId).get().getCode();
        long count = epicRepository.countByProductId(productId);
        return prodCode + "-" + (count + 1);
    }
}
