package com.uz.justplan.plan;

import com.uz.justplan.core.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class TimeLogging extends Auditable {
    @Column(nullable = false)
    private int minutes;
    private LocalDate loggedForDate;
    @Column(nullable = false)
    private Long releaseId;
    @Column(nullable = false)
    private Long epicId;
    @Column(nullable = false)
    private Long resourceId;
    private String comments;
    @Column(nullable = false)
    private boolean active;

    public TimeLogging() {
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public LocalDate getLoggedForDate() {
        return loggedForDate;
    }

    public void setLoggedForDate(LocalDate loggedForDate) {
        this.loggedForDate = loggedForDate;
    }

    public Long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Long releaseId) {
        this.releaseId = releaseId;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
