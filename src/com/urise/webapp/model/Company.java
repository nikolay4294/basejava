package com.urise.webapp.model;

public class Company {

    private String name;
    private String url;

    private final Experience experience = new Experience();


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
}
