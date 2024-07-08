package com.shylu.spring_practice.util;

import java.util.UUID;

public class RandomUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
