package com.example.urlshortener;

import java.util.UUID;

public class RandomIdGenerator {
    private static final int ID_SIZE = 10;

    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        String uuidWithoutDash = uuid.toString().replace("-", "");
        return uuidWithoutDash.substring(0, ID_SIZE);
    }
}
