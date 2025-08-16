package com.uz.justplan.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uz.justplan.lookup.ReleaseIteration;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class AddProductReq {

    @NotBlank(message = "It is required.")
    private Long companyId;
    private String name;
    private String code;
    private String emailProductManager;
    private Long productManagerId;
    private String emailProductOwner;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    //@Future(message = "First Delivery Date must be in the future")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    //@Future(message = "Last Date must be in the future")
    private LocalDate endDate;

    private int otherActivitiesPercentTime;

    @Enumerated(EnumType.STRING)
    private ReleaseIteration releaseIteration;

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

    public int getOtherActivitiesPercentTime() {
        return otherActivitiesPercentTime;
    }

    public void setOtherActivitiesPercentTime(int otherActivitiesPercentTime) {
        this.otherActivitiesPercentTime = otherActivitiesPercentTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReleaseIteration getReleaseIteration() {
        return releaseIteration;
    }

    public void setReleaseIteration(ReleaseIteration releaseIteration) {
        this.releaseIteration = releaseIteration;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailProductManager() {
        return emailProductManager;
    }

    public void setEmailProductManager(String emailProductManager) {
        this.emailProductManager = emailProductManager;
    }

    public String getEmailProductOwner() {
        return emailProductOwner;
    }

    public void setEmailProductOwner(String emailProductOwner) {
        this.emailProductOwner = emailProductOwner;
    }

    public Long getProductManagerId() {
        return productManagerId;
    }

    public void setProductManagerId(Long productManagerId) {
        this.productManagerId = productManagerId;
    }

    @Override
    public String toString() {
        return "AddProductReq{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", emailProductManager='" + emailProductManager + '\'' +
                ", emailProductOwner='" + emailProductOwner + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", otherActivitiesPercentTime=" + otherActivitiesPercentTime +
                ", releaseIteration=" + releaseIteration +
                '}';
    }
}
