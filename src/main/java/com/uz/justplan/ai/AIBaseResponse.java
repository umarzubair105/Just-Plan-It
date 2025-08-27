package com.uz.justplan.ai;

import java.util.ArrayList;
import java.util.List;

public class AIBaseResponse {
    String code;
    List<AIRouteDetail> routes;
    AIMessageType type;
    Long productId;
    Long releaseId;
    Long epicId;
    String message;

    public AIBaseResponse(AIMessageType type, String message, final AIRoute... routs) {
        this.type = type;
        this.message = message;
        this.routes = new ArrayList<>();
        for (int i = 0; i < routs.length; i++) {
            AIRoute route = routs[i];
            routes.add(new AIRouteDetail(AIUtil.routeLabel(route), route.getValue()));
        }
    }

    public List<AIRouteDetail> getRoutes() {
        return routes;
    }

    public void setRoutes(List<AIRouteDetail> routes) {
        this.routes = routes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public AIMessageType getType() {
        return type;
    }

    public void setType(AIMessageType type) {
        this.type = type;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
