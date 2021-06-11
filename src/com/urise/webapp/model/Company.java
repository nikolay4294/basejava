package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final List<Experience> list;
    private final String name;
    private final String url;

    public Company(List<Experience> list, String name, String url) {
        this.list = list;
        this.name = name;
        this.url = url;
    }

    public List<Experience> getList() {
        return list;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(list, company.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "Company{" +
                "list=" + list +
                '}';
    }
}
