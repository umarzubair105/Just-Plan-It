package com.uz.justplan.beans;

public class AddEpicReq {
    private String description;
    private Long productId;
    private String component;
    private String requiredBy;
    private String dateFormat;
    private String priority;
    private String comments;
    private String risks;
    private String valueGain;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRequiredBy() {
        return requiredBy;
    }

    public void setRequiredBy(String requiredBy) {
        this.requiredBy = requiredBy;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }

    public String getValueGain() {
        return valueGain;
    }

    public void setValueGain(String valueGain) {
        this.valueGain = valueGain;
    }
}
