package dev.flima.portfolio.modules.contact.enums;

public enum TypeContact {

    EMAIL("Email"),
    PHONE("Phone"),
    LOCALIZATION("Localization"),
    RESPONSETIME("Response Time"),
    AVAILABILITY("Availability");

    private final String name;

    TypeContact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
