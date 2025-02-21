package com.uz.justplan.services;

import com.uz.justplan.beans.ScheduleEpic;
import com.uz.justplan.beans.cal.ResourceCapInRelease;
import com.uz.justplan.lookup.Priority;
import com.uz.justplan.plan.Epic;
import com.uz.justplan.plan.EpicEstimate;
import com.uz.justplan.plan.Release;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlanningService {

    Map<Long, EpicEstimatePS> epicEstimateMap = new HashMap<>();// by epic Id
    Map<Long, ReleaseCapPS> releaseCapMap = new HashMap<>();// by release Id
    List<Release> unplannedReleases = null;
    //Release checkingRelease = null;
    //Map<Long, Map<Long, Map<Long,Integer>>> releaseCapacity = new HashMap<>();// relId, roleId, resourceId - minutes
    //Map<Long, Map<Long, Integer>> releaseOccupied = new HashMap<>();
    //Map<Long, Long> scheduleEpicsAddedToRelease = new HashMap<>();// epicid - > releaseId
    //Map<Long, Long> scheduleEpicsMovedToRelease = new HashMap<>();
    //List<Long> epicsRemovedFromRelease = new ArrayList<>();
    Map<Long, List<EpicEstimate>> assignEpicEstimate = new HashMap<>();// epicId is key
    ScheduleEpic scheduleEpic = new ScheduleEpic();
    @Autowired
    private CalculationService calService;

    private void addReleaseCapIfMissing(Release release) {
        if (!releaseCapMap.containsKey(release.getId())) {
            List<ResourceCapInRelease> resources = calService.getResourceCapInRelease(release.getId());
            releaseCapMap.put(release.getId(), new ReleaseCapPS(release, resources));
        }
    }

    private void addEpicEstimateIfMissing(Epic epic) {
        if (!epicEstimateMap.containsKey(epic.getId())) {
            List<EpicRoleEstimatePS> estimates = new ArrayList<>();
            // fill it from repository ** using epic estimates
            epicEstimateMap.put(epic.getId(), new EpicEstimatePS(epic, estimates));
        }
    }

    private boolean ifCapacityAvailableScheduleAndAssign(Epic epic, Release release) {
        addReleaseCapIfMissing(release);
        addEpicEstimateIfMissing(epic);
        List<EpicRoleEstimatePS> estimates = epicEstimateMap.get(epic.getId()).estimates;
        List<ResourceCapInRelease> resourceCapPS = releaseCapMap.get(release.getId()).resources;
        // first order to get minOccuppied users first
        resourceCapPS.sort((o1, o2) -> o1.minutesOccupied.compareTo(o2.minutesOccupied));
        List<String> resourceIdAlreadyAssignedWithRole = new ArrayList<>();//To handle 2 resources for epic estimate
        Map<Long, Long> epicEstIdToResourceId = new HashMap<>();
        estimates.forEach(estimate -> {
            resourceCapPS.stream()
                    .filter(resCap -> !resourceIdAlreadyAssignedWithRole.contains(resCap.roleId + "-" + resCap.resourceId)
                            && resCap.minutesCapacity >= resCap.minutesOccupied + estimate.minutes)
                    .forEach(resCap -> {
                        epicEstIdToResourceId.put(estimate.id, resCap.resourceId);
                    });
        });
        if (epicEstIdToResourceId.size() == estimates.size()) {
            //Updating response Object
            scheduleEpic.setReleaseIdToAddIn(release.getId());
            scheduleEpic.setAssignments(new ArrayList<>());
            estimates.forEach(estimate -> {
                ScheduleEpic.Assignment assignment = new ScheduleEpic.Assignment(
                        estimate.id, estimate.roleId, epicEstIdToResourceId.get(estimate.id), ScheduleEpic.ASSIGN_ADD
                );
                //Just updating following so we can use this service object to assign more Epics
                resourceCapPS.stream().filter(rCap -> rCap.resourceId.equals(assignment.getResourceId())
                        && rCap.roleId.equals(assignment.getRoleId())).forEach(rCap ->
                        rCap.minutesOccupied += estimate.minutes
                );
            });
            return true;
        }
        return false;
    }

    // get unplanned Epics
    public ScheduleEpic scheduleAndAssignEpicFirstTime(Epic epic) {
        scheduleEpic.setEpicId(epic.getId());
        List<Release> unplannedReleases = new ArrayList<>();//fill from repo sorted by start date **
        Assert.notEmpty(unplannedReleases, "There is no unplanned release");
        for (Release release : unplannedReleases) {
            if (ifCapacityAvailableScheduleAndAssign(epic, release)) {
                return scheduleEpic;
            }
        }
        // Use EpicRepository to get unplanned epics
        //List<Epic> epics = new ArrayList<>();
        List<EpicEstimate> epicEstimates = new ArrayList<>();
        List<Priority> priorities = new ArrayList<>();
        Map<Long, Integer> priMap = priorities.stream().collect(Collectors.toMap(p -> p.getId(), p -> p.getPriorityLevel()));
// make map of epicEstimates based on epicId from epicEstimates
        Map<Long, List<EpicEstimate>> epicEstMap = epicEstimates.stream().collect(Collectors.groupingBy(e -> e.getEpicId()));

        //sort epics by priority and requiredBy date if not null using Java streaming
        // make a list of tasks from epics
        /*
        List<Epic> tasks = epics.stream().map(epic -> new Task(epic.getId(), epic.getName(), priMap.get(epic.getPriority().getId()), epic.getRequiredByDate()))
               .collect(Collectors.toList());
*/
        // create a list of tasks that are not yet scheduled
  /*      List<Task> unscheduledTasks = tasks.stream().filter(t -> t.deliveryDate == null)
               .collect(Collectors.toList());
*/
        // schedule tasks in order of priority and delivery date if not null
        //...

        // update deliveryDate in epics list
        //..


/*
        epics.sort(Comparator.comparingInt((Task t) -> -t.priority)
                .thenComparing(t -> t.deliveryDate));*/
        return null;
    }

    private static class ReleaseCapPS {
        Release release;
        List<ResourceCapInRelease> resources;

        public ReleaseCapPS(Release release, List<ResourceCapInRelease> resources) {
            this.release = release;
            this.resources = resources;
        }
    }

    private static class EpicRoleEstimatePS {
        Long id;//epic estimateId
        Long roleId;
        int minutes;
        //optional in case already assigned
        Long resourceId;
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
