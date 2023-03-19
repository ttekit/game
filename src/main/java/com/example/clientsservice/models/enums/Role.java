package com.example.clientsservice.models.enums;

import java.util.Arrays;

public enum Role {
    USER,
    ADMIN;

    public static String[] getNames() {
        return Arrays.stream(Role.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
