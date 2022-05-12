package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

public enum ROLES {

    ROLE_USER("cliente"),
    ROLE_ADMIN("admin");

    private final String abbreviation;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<String, ROLES> lookup = new HashMap<String, ROLES>();

    static {
        for (ROLES d : ROLES.values()) {
            lookup.put(d.getAbbreviation(), d);
        }
    }

    private ROLES(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static ROLES get(String abbreviation) {
        return lookup.get(abbreviation);
    }

}
