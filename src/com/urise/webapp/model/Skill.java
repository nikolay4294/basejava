package com.urise.webapp.model;

import java.util.List;

public class Skill {

    private final List<String> skillList;

    public Skill(List<String> skillList) {
        this.skillList = skillList;
    }

    public List<String> getSkillList() {
        return skillList;
    }

    @Override
    public String toString() {
        return
                " Достижния и квалификация\n " + skillList +
                '}';
    }
}
