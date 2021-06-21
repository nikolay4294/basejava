package com.urise.webapp.model;

import java.util.List;

public class CompanySection {

    private final List<OrganizationSection> organizationSection;

    public CompanySection(List<OrganizationSection> organizationSection) {
        this.organizationSection = organizationSection;
    }


    @Override
    public String toString() {
        return "CompanySection{" +
                "company=" + organizationSection +
                '}';
    }
}
