package com.urise.webapp.model;

import java.time.YearMonth;

public class Experience {

    private final YearMonth startDate;
    private final YearMonth endDate;
    private final String heading;
    private final String description;
    private final String name;
    private final String url;

    public Experience(YearMonth startDate, YearMonth endDate, String heading, String description, String name, String url) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.heading = heading;
        this.description = description;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
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
}
