package com.urise.webapp.model;

public enum ContactType {
    PHONE("Номер телефона"),
    MAIL("Почта"),
    SKYPE("Скайп"),
    LINKED("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACK("Профиль Stackoverflow"),
    HOMEPAGE("Ссылка на домашнюю страницу");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}