package com.nhlstenden.test;

import org.junit.jupiter.api.BeforeAll;
import java.awt.GraphicsEnvironment;

public class BaseGuiTest {
    @BeforeAll
    static void setupHeadless() {
        if (GraphicsEnvironment.isHeadless()) {
            System.setProperty("java.awt.headless", "true");
        }
    }
} 