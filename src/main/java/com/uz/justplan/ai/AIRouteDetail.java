package com.uz.justplan.ai;

public class AIRouteDetail {
    String label;
    String routerLink;

    public AIRouteDetail(String label, String routerLink) {
        this.label = label;
        this.routerLink = routerLink;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }
}
