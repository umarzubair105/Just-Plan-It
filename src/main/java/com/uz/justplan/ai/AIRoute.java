package com.uz.justplan.ai;

public enum AIRoute {
    execution("execution"),
    planning("planning"),
    planned("planned"),
    executed("executed"),
    upload_resource("upload-resource"),
    mapping_roles_designation("mapping-roles-designation"),
    company_calendar("company-calendar"),
    product("product"),
    edit_product("edit-product"),
    upload_epic("upload-epic"),
    team_resource("team-resource"),
    product_resource("product-resource"),
    priority("priority"),
    epic_estimate("epic-estimate"),
    resource_leave("resource-leave"),
    resource("resource"),
    section("section"),
    p_dashboard("p-dashboard"),
    designation("designation"),
    role("role"),
    home("home"),
    contact_us("contact-us");


    private final String value;

    AIRoute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
