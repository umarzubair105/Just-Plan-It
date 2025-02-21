package com.uz.justplan.beans;

import java.util.List;

public class ScheduleEpic {
    public static final String ASSIGN_ADD = "add";
    public static final String ASSIGN_DEL = "del";
    public static final String ASSIGN_UPDATE = "update";

    Long epicId;
    Long releaseIdToAddIn;

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

    public Long getReleaseIdToAddIn() {
        return releaseIdToAddIn;
    }

    public void setReleaseIdToAddIn(Long releaseIdToAddIn) {
        this.releaseIdToAddIn = releaseIdToAddIn;
    }

    public List<Detail> getEffectedAreas() {
        return effectedAreas;
    }

    public void setEffectedAreas(List<Detail> effectedAreas) {
        this.effectedAreas = effectedAreas;
    }

    public static class Assignment {
        Long id; //epicEstimateId
        Long roleId;
        Long resourceId;
        String action;

        public Assignment(Long id, Long roleId, Long resourceId, String action) {
            this.id = id;
            this.roleId = roleId;
            this.resourceId = resourceId;
            this.action = action;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
