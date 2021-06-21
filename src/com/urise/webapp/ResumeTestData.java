package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;

public class ResumeTestData {

    public static void main(String[] args) {
        System.out.println(createResume("uuid1", "Григорий Кислин"));
    }

    public static Resume createResume(String uuid, String fullName) {

        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE, " +7(921) 855-0482\n");
        resume.addContact(ContactType.MAIL, " gkislin@yandex.ru\n");
        resume.addContact(ContactType.GITHUB, " https://github.com/gkislin\n");
        resume.addContact(ContactType.HOMEPAGE, " http://gkislin.ru\n");
        resume.addContact(ContactType.LINKED, " https://www.linkedin.com/in/gkislin\n");
        resume.addContact(ContactType.SKYPE, " grigory.kislin\n");
        resume.addContact(ContactType.STACK, " https://stackoverflow.com/users/548473/grigory-kislin\n");




        /*
        List<Organization> organizationList = new ArrayList<>();
        organizationList.add(new Organization("Java Online Projects", "https://javaops.ru/", YearMonth.of(2013, 10), YearMonth.of(2021, 6), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.\n"));
        organizationList.add(new Organization("Wrike", "https://www.wrike.com/", YearMonth.of(2014, 10), YearMonth.of(2016, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n"));
        organizationList.add(new Organization("RIT Center", " ", YearMonth.of(2012, 4), YearMonth.of(2014, 10), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python\n"));
        organizationList.add(new Organization("Luxoft (Deutsche Bank)", "https://career.luxoft.com/locations/russia/", YearMonth.of(2010, 12), YearMonth.of(2012, 4), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.\n"));
        organizationList.add(new Organization("Yota", "https://www.yota.ru/", YearMonth.of(2008, 6), YearMonth.of(2010, 12), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)\n"));
        organizationList.add(new Organization("Enkata", "https://www.pega.com", YearMonth.of(2007, 3), YearMonth.of(2008, 6), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).\n"));
        organizationList.add(new Organization("Siemens AG", "https://new.siemens.com/", YearMonth.of(2005, 1), YearMonth.of(2007, 2), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).\n"));
        organizationList.add(new Organization("Alcatel", "Alcatel", YearMonth.of(1997, 9), YearMonth.of(2005, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).\n"));

        organizationList.add(new Organization("Coursera", "https://www.coursera.org/", YearMonth.of(2013, 3), YearMonth.of(2013, 5), "\"Functional Programming Principles in Scala\" by Martin Odersky\n", " "));
        organizationList.add(new Organization("Luxoft", "https://career.luxoft.com/locations/russia/", YearMonth.of(2011, 3), YearMonth.of(2011, 4), "Курс - Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\n", " "));
        organizationList.add(new Organization("Siemens AG", "https://new.siemens.com/", YearMonth.of(2005, 1), YearMonth.of(2005, 4), "3 месяца обучения мобильным IN сетям (Берлин)\n", ""));
        organizationList.add(new Organization("Alcatel", "http://www.alcatel.ru/", YearMonth.of(1997, 9), YearMonth.of(1998, 3), "6 месяцев обучения цифровым телефонным сетям (Москва)\n", " "));
        organizationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/ru/", YearMonth.of(1993, 9), YearMonth.of(1996, 7), "Аспирантура (программист С, С++)\n", " "));
        organizationList.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/ru/", YearMonth.of(1987, 9), YearMonth.of(1993, 7), "Инженер (программист Fortran, C)\n", " "));
        organizationList.add(new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", YearMonth.of(1984, 9), YearMonth.of(1987, 6), "Закончил с отличием\n", " "));

        //ListSection listSection = new ListSection():

 */
        return resume;
    }
}