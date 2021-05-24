package com.urise.webapp.storage;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

public class ResumeTestData {

    public static void main(String[] args) {

        Resume resume = new Resume("uuid1", "Григорий Кислин" + "\n");

        resume.setContacts(ContactType.PHONE, " +7(921) 855-0482 " + "\n");
        resume.setContacts(ContactType.MAIL, " gkislin@yandex.ru " + "\n");
        resume.setContacts(ContactType.GITHUB, " https://github.com/gkislin " + "\n");
        resume.setContacts(ContactType.HOMEPAGE, " http://gkislin.ru " + "\n");
        resume.setContacts(ContactType.LINKED, " https://www.linkedin.com/in/gkislin " + "\n");
        resume.setContacts(ContactType.SKYPE, " grigory.kislin " + "\n");
        resume.setContacts(ContactType.STACK, " https://stackoverflow.com/users/548473/grigory-kislin " + "\n");

        resume.setSections(SectionType.EDUCATION, " 03/2013 - 05/2013\t\"Functional Programming Principles in Scala\" by Martin Odersky " + "\n");
        resume.setSections(SectionType.EXPERIENCE, " Автор проекта. Создание, организация и проведение Java онлайн проектов и стажировок. " + "\n");
        resume.setSections(SectionType.PERSONAL, " Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры. " + "\n");
        resume.setSections(SectionType.ACHIEVEMENT, " * С 2013 года: разработка проектов. Разработка Web приложения, Java Enterprise, Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA). Организация онлайн стажировок и ведение проектов. Более 1000 выпускников. " + "\n" +
                "\t" + "* Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk." + "\n" +
                "\t" + "* Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера." + "\n" +
                "\t" + "* Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга." + "\n" +
                "\t" + "* Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)" + "\n" +
                "\t" + "* Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа." + "\n");

        resume.setSections(SectionType.QUALIFICATIONS, " * JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 " + "\n" +
                "\t" + " * Version control: Subversion, Git, Mercury, ClearCase, Perforce" + "\n" +
                "\t" + " * Version control: Subversion, Git, Mercury, ClearCase, Perforce" + "\n" +
                "\t" + " * MySQL, SQLite, MS SQL, HSQLDB" + "\n" +
                "\t" + " * Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy" + "\n" +
                "\t" + " * XML/XSD/XSLT, SQL, C/C++, Unix shell scripts" + "\n" +
                "\t" + " * Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)." + "\n" +
                "\t" + " * Python: Django." + "\n" +
                "\t" + " * JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js" + "\n" +
                "\t" + " * Scala: SBT, Play2, Specs2, Anorm, Spray, Akka" + "\n" +
                "\t" + " * Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT." + "\n" +
                "\t" + " * Инструменты: Maven + plugin development, Gradle, настройка Ngnix," + "\n" +
                "\t" + " * администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer." + "\n" +
                "\t" + " * Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования" + "\n" +
                "\t" + " * Родной русский, английский \"upper intermediate\"" + "\n");
        resume.setSections(SectionType.OBJECTIVE, " * Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям " + "\n");
        System.out.println(resume);
    }
}
