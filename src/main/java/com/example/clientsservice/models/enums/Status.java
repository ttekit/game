package com.example.clientsservice.models.enums;

import java.util.Arrays;

public enum Status {
    ACTIVE,
    BANED;

    public static String[] getNames() {
        return Arrays.stream(Status.class.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
