package com.urise.webapp.model;

import java.time.YearMonth;

public class Experience {

    private YearMonth startDate;
    private YearMonth endDate;
    private String heading;
    private String description;
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public YearMonth getStartDate() {
        return startDate;
    }

    public void setStartDate(YearMonth startDate) {
        this.startDate = startDate;
    }

    public YearMonth getEndDate() {
        return endDate;
    }

    public void setEndDate(YearMonth endDate) {
        this.endDate = endDate;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
