package com.uz.justplan.lookup;

public enum CompanyType {
    IT_PRODUCT_BASE("IT_PRODUCT_BASE"),
    IT_PROJECT_BASE("IT_PROJECT_BASE"),
    OTHERS("OTHERS");
    private final String value;

    CompanyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
