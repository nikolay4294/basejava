package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySection implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<OrganizationSection> organizationSection;

    public CompanySection(List<OrganizationSection> organizationSection) {
        this.organizationSection = organizationSection;
    }

    public CompanySection() {

    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "company=" + organizationSection +
                '}';
    }
}
