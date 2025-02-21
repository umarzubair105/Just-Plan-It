package com.uz.justplan.lookup;

public enum WorkingHourScope {
    // Priority in calculating minutes is SPECIFIC_DATE, DATE_RANGE, WEEK_DAY, DEFAULT
    DEFAULT("DEFAULT"),//default
    DATE_RANGE("DATE_RANGE"),//startdate and end date required
    SPECIFIC_DATE("SPECIFIC_DATE"),// for 1 specific date
    WEEK_DAY("WEEK_DAY");// for specific day of week


    private final String value;

    WorkingHourScope(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
