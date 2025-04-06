package com.nhlstenden.style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import com.nhlstenden.style.StyleManager;
import com.nhlstenden.style.Style;


public class StyleManagerTest {
    private TestStyleManager styleManager;
    private TestStyle mockStyle;

    @BeforeEach
    void setUp() {
        styleManager = new TestStyleManager();
        mockStyle = mock(TestStyle.class);
    }

    @Test
    void testDefaultStyles() {
        // Test that default styles are created with correct parameters
        TestStyle style0 = styleManager.getStyle(0);
        assertNotNull(style0);
        assertEquals(0, style0.getIndent());
        assertEquals(Color.RED, style0.getColor());

        TestStyle style1 = styleManager.getStyle(1);
        assertNotNull(style1);
        assertEquals(20, style1.getIndent());
        assertEquals(Color.BLUE, style1.getColor());

        TestStyle style2 = styleManager.getStyle(2);
        assertNotNull(style2);
        assertEquals(50, style2.getIndent());
        assertEquals(Color.BLACK, style2.getColor());

        TestStyle style3 = styleManager.getStyle(3);
        assertNotNull(style3);
        assertEquals(70, style3.getIndent());
        assertEquals(Color.BLACK, style3.getColor());

        TestStyle style4 = styleManager.getStyle(4);
        assertNotNull(style4);
        assertEquals(90, style4.getIndent());
        assertEquals(Color.BLACK, style4.getColor());
    }

    @Test
    void testAddStyle() {
        // Given
        int level = 5;
        
        // When
        styleManager.addStyle(level, mockStyle);
        
        // Then
        assertEquals(mockStyle, styleManager.getStyle(level));
    }

    @Test
    void testGetStyle_ExistingLevel() {
        // Given
        int level = 5;
        styleManager.addStyle(level, mockStyle);
        
        // When
        TestStyle result = styleManager.getStyle(level);
        
        // Then
        assertEquals(mockStyle, result);
    }

    @Test
    void testGetStyle_NonExistingLevel() {
        // When
        TestStyle result = styleManager.getStyle(10);
        
        // Then
        // Should return the last defined style (level 4 by default)
        TestStyle style4 = styleManager.getStyle(4);
        assertEquals(style4, result);
    }

    @Test
    void testGetStyle_NegativeLevel() {
        // When
        TestStyle result = styleManager.getStyle(-1);
        
        // Then
        // Should return the last defined style (level 4 by default)
        TestStyle style4 = styleManager.getStyle(4);
        assertEquals(style4, result);
    }
} 