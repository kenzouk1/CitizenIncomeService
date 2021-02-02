package com.kian.model;

import java.util.EnumSet;

public enum Frequency {
    WEEK(1),
    TWO_WEEK(2),
    FOUR_WEEK(4),
    MONTH(null),
    QUARTER(13),
    YEAR(52);

    private Integer numberOfWeeks;

    Frequency(final Integer numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    public Integer getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public static EnumSet<Frequency> getMultiWeekFrequencies() {
        return EnumSet.of(TWO_WEEK, FOUR_WEEK, QUARTER, YEAR);
    }
}
