package com.urise.webapp.model;

import java.util.List;

public class CompanySection {

    private final List<Company> company;

    public CompanySection(List<Company> company) {
        this.company = company;
    }


    @Override
    public String toString() {
        return "CompanySection{" +
                "company=" + company +
                '}';
    }
}
