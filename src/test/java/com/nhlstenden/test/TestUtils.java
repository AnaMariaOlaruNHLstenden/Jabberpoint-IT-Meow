package com.nhlstenden.test;

import java.awt.GraphicsEnvironment;

public class TestUtils {
    static {
        // Set headless mode at JVM level
        System.setProperty("java.awt.headless", "true");
    }
    
    public static boolean isHeadless() {
        return GraphicsEnvironment.isHeadless();
    }
} 