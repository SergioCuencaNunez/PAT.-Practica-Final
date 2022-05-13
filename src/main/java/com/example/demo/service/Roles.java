package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

public enum Roles {

    ROLE_USER("cliente"),
    ROLE_ADMIN("admin");

    private final String abbreviation;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<String, Roles> lookup = new HashMap<String, Roles>();

    static {
        for (Roles d : Roles.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }

    private Roles(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Roles get(String abbreviation) {
        return lookup.get(abbreviation);
    }

}
