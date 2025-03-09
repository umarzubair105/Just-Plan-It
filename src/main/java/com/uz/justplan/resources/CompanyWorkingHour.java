package com.uz.justplan.resources;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.uz.justplan.config.DayOfWeekDeserializer;
import com.uz.justplan.core.Auditable;
import com.uz.justplan.lookup.WorkingHourScope;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity
public class CompanyWorkingHour extends Auditable implements Cloneable {

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private int minutes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkingHourScope scope;
    @Column(nullable = true)
    private LocalDate eventDate;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    @JsonDeserialize(using = DayOfWeekDeserializer.class)
    private DayOfWeek dayOfWeek;
    @Column(nullable = false)
    private boolean recurring; // For recurring events like annual holidays
    @Column(nullable = true)
    private LocalDate startDate;
    @Column(nullable = true)
    private LocalDate endDate;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean active;

    public CompanyWorkingHour() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //Person cloned = (Person) super.clone();
        //cloned.setAddress(new Address(this.address.getCity())); // Deep copy
        CompanyWorkingHour cloned = (CompanyWorkingHour) super.clone();
        cloned.setId(null);
        return cloned;
    }

    public WorkingHourScope getScope() {
        return scope;
    }

    public void setScope(WorkingHourScope scope) {
        this.scope = scope;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
