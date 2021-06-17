package com.urise.webapp.model;

import java.time.YearMonth;

public class Experience {

    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String heading;
    private final String description;

    public Experience(YearMonth startDate, YearMonth endDate, String heading, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.heading = heading;
        this.description = description;
    }

    public YearMonth getStartDate() {
        return startDate;
    }

    public YearMonth getEndDate() {
        return endDate;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Опыт работы и образование\n" +
                "startDate=" + startDate +
                " endDate=" + endDate +
                " heading='" + heading + '\'' +
                " description='" + description;
    }
}
