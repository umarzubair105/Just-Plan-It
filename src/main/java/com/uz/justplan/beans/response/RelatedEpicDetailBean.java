package com.uz.justplan.beans.response;

import com.uz.justplan.lookup.EpicStatus;
import com.uz.justplan.plan.Epic;

import java.time.LocalDate;

public class RelatedEpicDetailBean {
    Long id;
    String code, title;
    EpicStatus status;
    LocalDate releaseDate;

    public RelatedEpicDetailBean(Epic epic, LocalDate releaseDate) {
        this.id = epic.getId();
        this.code = epic.getCode();
        this.title = epic.getTitle();
        this.status = epic.getStatus();
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EpicStatus getStatus() {
        return status;
    }

    public void setStatus(EpicStatus status) {
        this.status = status;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
