package com.nhlstenden.test;

import java.awt.GraphicsEnvironment;

public class TestUtils {
    private static boolean isHeadless = GraphicsEnvironment.isHeadless();
    
    public static void setupHeadless() {
        if (isHeadless) {
            System.setProperty("java.awt.headless", "true");
        }
    }
    
    public static boolean isHeadless() {
        return isHeadless;
    }
} 