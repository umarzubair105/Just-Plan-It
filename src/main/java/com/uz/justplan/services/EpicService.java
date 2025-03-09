package com.uz.justplan.services;

import com.uz.justplan.beans.ScheduleEpic;
import com.uz.justplan.beans.cal.ResourceCapInRelease;
import com.uz.justplan.plan.Epic;
import com.uz.justplan.plan.EpicEstimate;
import com.uz.justplan.plan.EpicEstimateRepository;
import com.uz.justplan.plan.Release;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EpicService {
    private static final Logger log = LoggerFactory.getLogger(EpicService.class);
    Map<Long, EpicEstimatePS> epicEstimateMap = new HashMap<>();// by epic Id
    Map<Long, ReleaseCapPS> releaseCapMap = new HashMap<>();// by release Id
    List<Release> unplannedReleases = null;
    //Release checkingRelease = null;
    //Map<Long, Map<Long, Map<Long,Integer>>> releaseCapacity = new HashMap<>();// relId, roleId, resourceId - minutes
    //Map<Long, Map<Long, Integer>> releaseOccupied = new HashMap<>();
    //Map<Long, Long> scheduleEpicsAddedToRelease = new HashMap<>();// epicid - > releaseId
    //Map<Long, Long> scheduleEpicsMovedToRelease = new HashMap<>();
    //List<Long> epicsRemovedFromRelease = new ArrayList<>();
    Map<Long, List<EpicRoleEstimatePS>> assignEpicEstimate = new HashMap<>();// epicId is key
    ScheduleEpic scheduleEpic = new ScheduleEpic();
    @Autowired
    private ReleaseService calService;
    @Autowired
    private EpicEstimateRepository epicEstRepo;

    public ScheduleEpic scheduleAndAssignEpicFirstTime(Epic epic, List<Release> unplannedReleases) {
        scheduleEpic.setEpicId(epic.getId());
        Assert.notEmpty(unplannedReleases, "There is no unplanned release");
        for (Release release : unplannedReleases) {
            if (ifCapacityAvailableScheduleAndAssign(epic, release, false)) {
                return scheduleEpic;
            }
        }
        //Map<Long, Integer> priMap = priorities.stream().collect(Collectors.toMap(p -> p.getId(), p -> p.getPriorityLevel()));
        //Map<Long, List<EpicEstimate>> epicEstMap = epicEstimates.stream().collect(Collectors.groupingBy(e -> e.getEpicId()));
        return null;
    }

    public ScheduleEpic scheduleAndAssignEpicForcefully(Epic epic, Release release) {
        scheduleEpic.setEpicId(epic.getId());
        if (ifCapacityAvailableScheduleAndAssign(epic, release, true)) {
            return scheduleEpic;
        }
        return null;
    }

    private void addReleaseCapIfMissing(Release release) {
        if (!releaseCapMap.containsKey(release.getId())) {
            List<ResourceCapInRelease> resources = calService.getResourceCapInRelease(release.getId());
            releaseCapMap.put(release.getId(), new ReleaseCapPS(release, resources));
        }
    }

    private void addEpicEstimateIfMissing(Epic epic) {
        if (!epicEstimateMap.containsKey(epic.getId())) {
            List<EpicEstimate> estimates = epicEstRepo.findByEpicIdAndActiveIsTrue(epic.getId())
                    .stream().filter(e -> e.getMinutes() > 0).collect(Collectors.toList());
            ;
            List<EpicRoleEstimatePS> estimateBean = new ArrayList<>();
            AtomicInteger index = new AtomicInteger();
            estimates.forEach(e -> {
                for (int i = 0; i < e.getResources(); i++) {
                    index.getAndIncrement();
                    estimateBean.add(new EpicRoleEstimatePS(index.get(), e.getRoleId(), e.getMinutes() / e.getResources()));
                }
            });
            // fill it from repository ** using epic estimates
            epicEstimateMap.put(epic.getId(), new EpicEstimatePS(epic, estimateBean));
        }
    }

    private boolean ifCapacityAvailableScheduleAndAssign(Epic epic, Release release, boolean assignForceFull) {
        addReleaseCapIfMissing(release);
        addEpicEstimateIfMissing(epic);
        List<EpicRoleEstimatePS> estimates = epicEstimateMap.get(epic.getId()).estimates;
        List<ResourceCapInRelease> resourceCapPS = releaseCapMap.get(release.getId()).resources;
        // first order to get minOccuppied users first
        resourceCapPS.sort((o1, o2) -> o1.prodBasedAssignedTime.compareTo(o2.prodBasedAssignedTime));
        List<String> resourceIdAlreadyAssignedWithRole = new ArrayList<>();//To handle 2 resources for epic estimate
        Map<Integer, Long> epicEstIdToResourceId = new HashMap<>();
        log.info(">>>>>>>Estimate--------: " + epicEstIdToResourceId.size() + "," + estimates.size());
        estimates.forEach(estimate -> {
            log.info(">>>>>>>Estimate: " + estimate);
            resourceCapPS.forEach(resCap -> {
                if (estimate.roleId.equals(resCap.roleId)) {
                    log.info(">>>>>>>>>>>>resourceCap: " + resCap);
                    log.info(">>>>>>>>>>>>resourceCap 1: " + (!assignForceFull
                            && resCap.prodBasedAssignableTime >= resCap.prodBasedAssignedTime + estimate.minutes));
                    log.info(">>>>>>>>>>>>resourceCap 2: " + (assignForceFull
                            && resCap.prodBasedAssignableTime >= 0));
                }
            });
            resourceCapPS.stream()
                    .filter(resCap ->
                            estimate.roleId.equals(resCap.roleId)
                                    && !epicEstIdToResourceId.containsKey(estimate.index)
                                    && !resourceIdAlreadyAssignedWithRole.contains(resCap.roleId + "-" + resCap.resourceId)
                                    && ((!assignForceFull
                                    && resCap.prodBasedAssignableTime >= resCap.prodBasedAssignedTime + estimate.minutes)
                                    || (assignForceFull
                                    && resCap.prodBasedAssignableTime >= 0)))
                    .forEach(resCap -> {
                        log.info(">>>>>>>>>>>>>>>>>>>>resCap: " + resCap);
                        resourceIdAlreadyAssignedWithRole.add(resCap.roleId + "-" + resCap.resourceId);
                        epicEstIdToResourceId.put(estimate.index, resCap.resourceId);
                    });
        });
        log.info(">>>>>>>Estimate--------: " + epicEstIdToResourceId.size() + "," + estimates.size());

        if (epicEstIdToResourceId.size() == estimates.size()) {
            //Updating response Object
            scheduleEpic.setReleaseToAddIn(release);
            scheduleEpic.setAssignments(new ArrayList<>());
            estimates.forEach(estimate -> {
                ScheduleEpic.Assignment assignment = new ScheduleEpic.Assignment(
                        estimate.roleId, epicEstIdToResourceId.get(estimate.index), ScheduleEpic.ASSIGN_ADD, estimate.minutes
                );
                scheduleEpic.getAssignments().add(assignment);
                //Just updating following so we can use this service object to assign more Epics
                resourceCapPS.stream().filter(rCap -> rCap.resourceId.equals(assignment.getResourceId())
                        && rCap.roleId.equals(assignment.getRoleId())).forEach(rCap ->
                        rCap.prodBasedAssignedTime += estimate.minutes
                );
            });
            return true;
        }
        return false;
    }
    // get unplanned Epics


    private static class ReleaseCapPS {
        Release release;
        List<ResourceCapInRelease> resources;

        public ReleaseCapPS(Release release, List<ResourceCapInRelease> resources) {
            this.release = release;
            this.resources = resources;
        }
    }

    private static class EpicRoleEstimatePS {
        Long roleId;
        int minutes;
        int index;

        public EpicRoleEstimatePS(int index, Long roleId, int minutes) {
            this.roleId = roleId;
            this.minutes = minutes;
            this.index = index;
        }

        @Override
        public String toString() {
            return "EpicRoleEstimatePS{" +
                    "roleId=" + roleId +
                    ", minutes=" + minutes +
                    ", index=" + index +
                    '}';
        }
    }

    private static class EpicEstimatePS {
        Epic epic;
        List<EpicRoleEstimatePS> estimates;

        public EpicEstimatePS(Epic epic, List<EpicRoleEstimatePS> estimates) {
            this.epic = epic;
            this.estimates = estimates;
        }
    }
}
