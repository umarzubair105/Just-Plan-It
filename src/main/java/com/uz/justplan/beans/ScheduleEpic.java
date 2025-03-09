package com.uz.justplan.beans;

import com.uz.justplan.plan.Release;

import java.util.List;

public class ScheduleEpic {
    public static final String ASSIGN_ADD = "add";
    public static final String ASSIGN_DEL = "del";
    public static final String ASSIGN_UPDATE = "update";

    Long epicId;
    Release releaseToAddIn;

    List<Assignment> assignments;
    List<Detail> effectedAreas;

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Release getReleaseToAddIn() {
        return releaseToAddIn;
    }

    public void setReleaseToAddIn(Release releaseToAddIn) {
        this.releaseToAddIn = releaseToAddIn;
    }

    public List<Detail> getEffectedAreas() {
        return effectedAreas;
    }

    public void setEffectedAreas(List<Detail> effectedAreas) {
        this.effectedAreas = effectedAreas;
    }

    public static class Assignment {
        Long roleId;
        Long resourceId;
        int minutes;
        String action;

        public Assignment(Long roleId, Long resourceId, String action, int minutes) {
            this.roleId = roleId;
            this.resourceId = resourceId;
            this.action = action;
            this.minutes = minutes;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }

        public Long getResourceId() {
            return resourceId;
        }

        public void setResourceId(Long resourceId) {
            this.resourceId = resourceId;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }
    }

    public static class Detail {
        Long epicId;
        String action;
        Long currentReleaseId;
        Long newReleaseId;
        List<Assignment> assignments;

        public Detail(Long epicId, String action, Long currentReleaseId, Long newReleaseId, List<Assignment> assignments) {
            this.epicId = epicId;
            this.action = action;
            this.currentReleaseId = currentReleaseId;
            this.newReleaseId = newReleaseId;
            this.assignments = assignments;
        }

        public List<Assignment> getAssignments() {
            return assignments;
        }

        public void setAssignments(List<Assignment> assignments) {
            this.assignments = assignments;
        }

        public Long getEpicId() {
            return epicId;
        }

        public void setEpicId(Long epicId) {
            this.epicId = epicId;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public Long getCurrentReleaseId() {
            return currentReleaseId;
        }

        public void setCurrentReleaseId(Long currentReleaseId) {
            this.currentReleaseId = currentReleaseId;
        }

        public Long getNewReleaseId() {
            return newReleaseId;
        }

        public void setNewReleaseId(Long newReleaseId) {
            this.newReleaseId = newReleaseId;
        }
    }

}
