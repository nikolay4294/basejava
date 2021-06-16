package com.urise.webapp.model;

public class SingleLineSection {

    private final String personal;

    public SingleLineSection(String singleLineSection) {
        this.personal = singleLineSection;
    }

    public String getPersonal() {
        return personal;
    }

    @Override
    public String toString() {
        return "SingleLineSection{" +
                " Личные качества и позиция " + personal + '\'' +
                '}';
    }
}
