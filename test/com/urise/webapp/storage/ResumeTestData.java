package com.urise.webapp.storage;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;

public class ResumeTestData {

    public static void main(String[] args) {

        Resume resume = new Resume("Григорий Кислин");
        resume.setContacts(ContactType.PHONE.getTitle(),"+7(921) 855-0482");
        resume.setContacts(ContactType.MAIL.getTitle(), "gkislin@yandex.ru");
        resume.setContacts(ContactType.GITHUB.getTitle(), "https://github.com/gkislin");
        resume.setContacts(ContactType.HOMEPAGE.getTitle(), "http://gkislin.ru/");
        resume.setContacts(ContactType.LINKED.getTitle(), "https://www.linkedin.com/in/gkislin");
        resume.setContacts(ContactType.SKYPE.getTitle(), "grigory.kislin");
        resume.setContacts(ContactType.STACK.getTitle(), "https://stackoverflow.com/users/548473/grigory-kislin");
    }

}
