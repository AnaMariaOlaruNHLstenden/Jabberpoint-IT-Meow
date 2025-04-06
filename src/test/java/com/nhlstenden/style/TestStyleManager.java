package com.nhlstenden.style;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map; 
import com.nhlstenden.style.TestStyle;

class TestStyleManager {
    private final Map<Integer, TestStyle> STYLES = new HashMap<>();

    public TestStyleManager() {
        // Default styles
        addStyle(0, new TestStyle(0, Color.RED, 48, 20));
        addStyle(1, new TestStyle(20, Color.BLUE, 40, 10));
        addStyle(2, new TestStyle(50, Color.BLACK, 36, 10));
        addStyle(3, new TestStyle(70, Color.BLACK, 30, 10));
        addStyle(4, new TestStyle(90, Color.BLACK, 24, 10));
    }

    public void addStyle(int level, TestStyle style) {
        STYLES.put(level, style);
    }

    public TestStyle getStyle(int level) {
        return STYLES.getOrDefault(level, STYLES.get(STYLES.size() - 1));
    }
} 