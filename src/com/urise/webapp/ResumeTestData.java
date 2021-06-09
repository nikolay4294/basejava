package com.urise.webapp;

import com.urise.webapp.model.*;

public class ResumeTestData {

    public static void main(String[] args) {

        System.out.println(createResume("uuid1", "Григорий Кислин"));
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContacts(ContactType.PHONE, " +7(921) 855-0482 " + "\n");
        resume.setContacts(ContactType.MAIL, " gkislin@yandex.ru " + "\n");
        resume.setContacts(ContactType.GITHUB, " https://github.com/gkislin " + "\n");
        resume.setContacts(ContactType.HOMEPAGE, " http://gkislin.ru " + "\n");
        resume.setContacts(ContactType.LINKED, " https://www.linkedin.com/in/gkislin " + "\n");
        resume.setContacts(ContactType.SKYPE, " grigory.kislin " + "\n");
        resume.setContacts(ContactType.STACK, " https://stackoverflow.com/users/548473/grigory-kislin " + "\n");

        resume.setSections(SectionType.OBJECTIVE, " Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям " + "\n");
        resume.setSections(SectionType.PERSONAL, " Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры. " + "\n");
        resume.setSections(SectionType.ACHIEVEMENT, " * С 2013 года: разработка проектов. Разработка Web приложения, Java Enterprise, Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA). Организация онлайн стажировок и ведение проектов. Более 1000 выпускников. " + "\n" +
                "\t* Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk." + "\n" +
                "\t* Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера." + "\n" +
                "\t* Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга." + "\n" +
                "\t* Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django)" + "\n" +
                "\t* Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа." + "\n");
        resume.setSections(SectionType.QUALIFICATIONS, " * JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 " + "\n" +
                "\t* Version control: Subversion, Git, Mercury, ClearCase, Perforce\n" +
                "\t* Version control: Subversion, Git, Mercury, ClearCase, Perforce\n" +
                "\t* MySQL, SQLite, MS SQL, HSQLDB\n" +
                "\t* Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy\n" +
                "\t* XML/XSD/XSLT, SQL, C/C++, Unix shell scripts\n" +
                "\t* Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)." + "\n" +
                "\t* Python: Django.\n" +
                "\t* JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js\n" +
                "\t* Scala: SBT, Play2, Specs2, Anorm, Spray, Akka\n" +
                "\t* Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT." + "\n" +
                "\t* Инструменты: Maven + plugin development, Gradle, настройка Ngnix,\n" +
                "\t* администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer." + "\n" +
                "\t* Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования" + "\n" +
                "\t* Родной русский, английский \"upper intermediate\"\n");
        resume.setSections(SectionType.EXPERIENCE, " Java Online Projects\n\t* 10/2013 - Сейчас Автор проекта\n\t* Создание, организация и проведение Java онлайн проектов и стажировок. " + "\n" +
                "\t" + "Wrike\n\t* 10/2014 - 01/2016   Старший разработчик (backend)\n\t* Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO\n." +
                "\tRIT Center\n\t* 04/2012 - 10/2014   Java архитектор\n\t* Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python\n" +
                "\tLuxoft (Deutsche Bank)\n\t* 12/2010 - 04/2012   Ведущий программист\n\t* Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.\n" +
                "\tYota\n\t* 06/2008 - 12/2010  Ведущий специалист\n\t* Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)\n" +
                "\tEnkata\n\t* 03/2007 - 06/2008   Разработчик ПО\n\t* Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).\n" +
                "\tSiemens AG\n\t* 01/2005 - 02/2007   Разработчик ПО\n\t* Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).\n" +
                "\tAlcatel\n\t* 09/1997 - 01/2005   Инженер по аппаратному и программному тестированию\n\t* Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        resume.setSections(SectionType.EDUCATION, "Coursera\n\t* 03/2013 - 05/2013   \"Functional Programming Principles in Scala\" by Martin Odersky\n" +
                "\tLuxoft\n\t* 03/2011 - 04/2011\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"\n" +
                "\tSiemens AG\n\t* 01/2005 - 04/2005\t3 месяца обучения мобильным IN сетям (Берлин)\n" +
                "\tAlcatel\n\t* 09/1997 - 03/1998\t6 месяцев обучения цифровым телефонным сетям (Москва)\n" +
                "\tСанкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики\n\t* 09/1993 - 07/1996\tАспирантура (программист С, С++)\n\t* 09/1987 - 07/1993\tИнженер (программист Fortran, C)\n" +
                "\tЗаочная физико-техническая школа при МФТИ\n\t* 09/1984 - 06/1987\tЗакончил с отличием");
        return resume;
    }
}
