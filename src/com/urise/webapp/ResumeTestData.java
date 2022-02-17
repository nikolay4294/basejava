package com.urise.webapp;

import com.urise.webapp.model.*;

public class ResumeTestData {

    public static void main(String[] args) {
        System.out.println(createResume("uuid1", "Григорий Кислин"));
    }

    public static Resume createResume(String uuid, String fullName) {

        Resume resume = new Resume(uuid, fullName);

        resume.setContact(ContactType.PHONE, " +7(921) 855-0482\n");
        resume.setContact(ContactType.MAIL, " gkislin@yandex.ru\n");
        resume.setContact(ContactType.GITHUB, " https://github.com/gkislin\n");
        resume.setContact(ContactType.HOMEPAGE, " http://gkislin.ru\n");
        resume.setContact(ContactType.LINKED, " https://www.linkedin.com/in/gkislin\n");
        resume.setContact(ContactType.SKYPE, " grigory.kislin\n");
        resume.setContact(ContactType.STACK, " https://stackoverflow.com/users/548473/grigory-kislin\n");

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        resume.setSection(SectionType.PERSONAL, new TextSection("Personal data"));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection("Achievement11", "Achievement12", "Achievement13"));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        /*
        resume.addSections(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        resume.addSections(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://organization12.ru")));
        resume.addContact(ContactType.SKYPE, "skype2");
        resume.addContact(ContactType.PHONE, "22222");
        resume.addSections(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));

        */
        return resume;
    }
}