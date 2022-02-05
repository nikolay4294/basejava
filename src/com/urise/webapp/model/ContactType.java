package com.urise.webapp.model;

public enum ContactType {
    PHONE("Номер телефона"),
    MAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return "<a href='mailto: " + value + "'>" + value + "<a>";
        }
    },
    SKYPE("Скайп") {
        @Override
        public String toHtml0(String value) {
            return "<a href='skype: " + value + "'>" + value + "<a>";
        }
    },
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

    public String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }
}