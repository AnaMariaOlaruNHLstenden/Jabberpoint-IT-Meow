package com.nhlstenden.test;

import org.junit.jupiter.api.BeforeAll;

public class TestConfiguration {
    @BeforeAll
    static void setup() {
        System.setProperty("java.awt.headless", "true");
    }
} 