package com.urise.webapp.model;

import java.io.Serializable;
import java.util.List;

public class CompanySection implements Serializable {
    private static final long serialVersionUID = 1L;

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
