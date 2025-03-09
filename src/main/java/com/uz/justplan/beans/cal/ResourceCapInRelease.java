package com.uz.justplan.beans.cal;

public class ResourceCapInRelease {
    public Long roleId;
    public String roleName;
    public Long resourceId;
    public String resourceName;

    public Integer workingDays;
    public Integer availableTime;
    public Integer prodBasedAssignableTime;
    public Integer prodBasedAssignedTime;
    public Integer prodBasedExtraTime;

    //public Integer assignableTime;
    //public Integer assignedTime;
    //public Integer extraTime;

    @Override
    public String toString() {
        return "ResourceCapInRelease{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", workingDays=" + workingDays +
                ", availableTime=" + availableTime +
                ", prodBasedAssignableTime=" + prodBasedAssignableTime +
                ", prodBasedAssignedTime=" + prodBasedAssignedTime +
                ", prodBasedExtraTime=" + prodBasedExtraTime +
                '}';
    }
}
