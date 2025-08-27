package com.uz.justplan.ai;

public class AIUtil {
    public static String message(final String message, final AIRoute... routs) {
        String display = message;
        if (routs == null) {
            return display;
        }
        for (int i = 0; i < routs.length; i++) {
            AIRoute route = routs[i];
            display = display.replace("$" + i,
                    "<a class=\"nav-link\" routerLink=\"/" + route.getValue() + "\">" + routeLabel(route) + "</a>");
        }
        return display;
    }

    /**
     * message(
     * "Navigate: $0 → $1 → $2",
     * AIRoute.home,
     * AIRoute.product,
     * AIRoute.priority
     * );
     *
     * @param route
     * @return
     */
    public static String routeLabel(final AIRoute route) {
        switch (route) {
            case execution:
                return "Dashboard";
            case planning:
                return "Backlog";
            case planned:
                return "Planned";
            case executed:
                return "History";
            case upload_resource:
                return "Upload Resources";
            case mapping_roles_designation:
                return "Role Mapping";
            case company_calendar:
                return "Company Calendar";
            case product:
                return "New Product";
            case edit_product:
                return "Products";
            case upload_epic:
                return "Upload Deliverables";
            case team_resource:
                return "Define Team";
            case product_resource:
                return "Time Allocation";
            case priority:
                return "Priorities";
            case epic_estimate:
                return "Epic Estimate";
            case resource_leave:
                return "Leaves";
            case resource:
                return "Resource Management";
            case section:
                return "Components";
            case p_dashboard:
                return "Personal Dashboard";
            case designation:
                return "Designations";
            case role:
                return "Roles";
            case home:
                return "Home";
            case contact_us:
                return "Contact Us";
            default:
                return route.getValue();
        }
    }

}
