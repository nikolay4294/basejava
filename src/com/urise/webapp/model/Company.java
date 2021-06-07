package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final List<Experience> list;

    public Company(List<Experience> list) {
        this.list = list;
    }

    public List<Experience> getList() {
        return list;
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
