package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        System.out.println(createResume("uuid1", "Григорий Кислин"));
    }

    public static Resume createResume(String uuid, String fullName) {

        Resume resume = new Resume(uuid, fullName);

        resume.setContacts(ContactType.PHONE, " +7(921) 855-0482\n");
        resume.setContacts(ContactType.MAIL, " gkislin@yandex.ru\n");
        resume.setContacts(ContactType.GITHUB, " https://github.com/gkislin\n");
        resume.setContacts(ContactType.HOMEPAGE, " http://gkislin.ru\n");
        resume.setContacts(ContactType.LINKED, " https://www.linkedin.com/in/gkislin\n");
        resume.setContacts(ContactType.SKYPE, " grigory.kislin\n");
        resume.setContacts(ContactType.STACK, " https://stackoverflow.com/users/548473/grigory-kislin\n");


        List<Experience> experiences = new ArrayList<>();
        experiences.add(new Experience(YearMonth.of(2013, 10), YearMonth.of(2021, 6), "Java Online Projects", "Автор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.\n"));
        experiences.add(new Experience(YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Wrike", "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n"));
        experiences.add(new Experience(YearMonth.of(2012, 4), YearMonth.of(2014, 10), "RIT Center", "Java архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python\n"));
        experiences.add(new Experience(YearMonth.of(2010, 12), YearMonth.of(2012, 4), "Luxoft (Deutsche Bank)", "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.\n"));
        experiences.add(new Experience(YearMonth.of(2008, 6), YearMonth.of(2010, 12), "Yota", "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)\n"));
        experiences.add(new Experience(YearMonth.of(2007, 3), YearMonth.of(2008, 6), "Enkata", "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).\n"));
        experiences.add(new Experience(YearMonth.of(2005, 1), YearMonth.of(2007, 2), "Siemens AG", "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).\n"));
        experiences.add(new Experience(YearMonth.of(1997, 9), YearMonth.of(2005, 1), "Alcatel", "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).\n"));

        experiences.add(new Experience(YearMonth.of(2013, 3), YearMonth.of(2013, 5), "Coursera", "\"Functional Programming Principles in Scala\" by Martin Odersky\n"));
        experiences.add(new Experience(YearMonth.of(2011, 3), YearMonth.of(2011, 4), "Luxoft", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"\n"));
        experiences.add(new Experience(YearMonth.of(2005, 1), YearMonth.of(2005, 4), "Siemens AG", "3 месяца обучения мобильным IN сетям (Берлин)\n"));
        experiences.add(new Experience(YearMonth.of(1997, 9), YearMonth.of(1998, 3), "Alcatel", "6 месяцев обучения цифровым телефонным сетям (Москва)\n"));
        experiences.add(new Experience(YearMonth.of(1993, 9), YearMonth.of(1996, 7), "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "Аспирантура (программист С, С++)\n"));
        experiences.add(new Experience(YearMonth.of(1987, 9), YearMonth.of(1993, 7), "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "Инженер (программист Fortran, C)\n"));
        experiences.add(new Experience(YearMonth.of(1984, 9), YearMonth.of(1987, 6), "Заочная физико-техническая школа при МФТИ", "Закончил с отличием\n"));

        List<Company> companies = new ArrayList<>();
        companies.add(new Company(experiences, "* Java Online Projects", "https://javaops.ru/\n"));
        companies.add(new Company(experiences, "* Wrike", "https://www.wrike.com/\n"));
        companies.add(new Company(experiences, "* RIT Center", "https://www.rit.com/\n"));
        companies.add(new Company(experiences, "* Luxoft (Deutsche Bank)", "https://career.luxoft.com/locations/russia/\n"));
        companies.add(new Company(experiences, "* Yota", "https://www.Yota.com\n"));
        companies.add(new Company(experiences, "* Enkata", "https://www.pega.com/\n"));
        companies.add(new Company(experiences, "* Siemens AG", "https://new.siemens.com/ru/ru.html\n"));
        companies.add(new Company(experiences, "* Alcatel", "http://www.alcatel.ru/\n"));

        SingleLineSection personal = new SingleLineSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n" + "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.\n");

        List<String> skills = new ArrayList<>();
        skills.add("* С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
        skills.add("* Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n");
        skills.add("* Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n");
        skills.add("* Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n");
        skills.add("* Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).\n");
        skills.add("* Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");

        skills.add("* JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n");
        skills.add("* Version control: Subversion, Git, Mercury, ClearCase, Perforce\n");
        skills.add("* DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle\n");
        skills.add("* MySQL, SQLite, MS SQL, HSQLDB\n");
        skills.add("* Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy\n");
        skills.add("* XML/XSD/XSLT, SQL, C/C++, Unix shell scripts\n");
        skills.add("* Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).\n");
        skills.add("* Python: Django.\n");
        skills.add("* JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js\n");
        skills.add("* Scala: SBT, Play2, Specs2, Anorm, Spray, Akka\n");
        skills.add("* Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.\n");
        skills.add("* Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n");
        skills.add("* администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.\n");
        skills.add("* Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования\n");
        skills.add("* Родной русский, английский \"upper intermediate\"\n");
        Skill skill = new Skill(skills);

        CompanySection companySection = new CompanySection(companies);

        resume.setSections(SectionType.OBJECTIVE, personal);
        resume.setSections(SectionType.QUALIFICATIONS, skill);
        resume.setSections(SectionType.EXPERIENCE, experiences);
        return resume;
    }
}