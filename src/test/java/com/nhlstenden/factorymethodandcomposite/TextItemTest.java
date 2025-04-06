package com.nhlstenden.factorymethodandcomposite;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;
import com.nhlstenden.style.StyleManager;
import com.nhlstenden.style.Style;

class TextItemTest{
    
    @Test
    void testTextItemAttributes() {
        SlideItemFactory factory = new TextItemCreator();
        SlideItem slideItem = factory.createSlideItem(3, "Test Content");
        
        assertEquals(3, slideItem.getLevel());
        assertEquals("Test Content", ((TextItem)slideItem).getText(), "TextItem should store the correct content.");
    }
    
    @Test
    void testTextItemDrawUsingRealGraphics() {
        // Create a real graphics context (BufferedImage as a canvas)
        BufferedImage canvas = new BufferedImage(300, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = canvas.createGraphics();
        graphics.setFont(new Font("Arial", Font.PLAIN, 12));
        // Fill with white background to make it easier to detect changes
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        // Create a TextItem instance
        TextItem textItem = new TextItem(1, "Hello, World!");
        StyleManager styleManager = new StyleManager();
        
        // Call the draw method
        textItem.draw(graphics, 0, 0, 1.0f, null, styleManager);
        
        // Determine the approximate position where text should be drawn
        Style style = styleManager.getStyle(1);
        int approxTextX = (int)(style.getIndent());  // Based on the indent
        int approxTextY = (int)(style.getLeading() + 15); // Leading plus approximate ascent
        
        // Check multiple pixels in the area where text should be drawn
        boolean foundNonWhitePixel = false;
        for (int x = approxTextX; x < approxTextX + 100 && !foundNonWhitePixel; x += 5) {
            for (int y = approxTextY - 10; y < approxTextY + 10 && !foundNonWhitePixel; y += 2) {
                if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight()) {
                    Color pixelColor = new Color(canvas.getRGB(x, y), true);
                    if (!pixelColor.equals(Color.WHITE)) {
                        foundNonWhitePixel = true;
                        break;
                    }
                }
            }
        }
        
        assertTrue(foundNonWhitePixel, "No text was found on the canvas");
        
        // Clean up resources
        graphics.dispose();
    }
    
    @Test
    void testTextItemBoundingBox() {
        // Create a real Graphics2D from a temporary image
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = tempImage.createGraphics();
        
        // Create StyleManager with real styles
        StyleManager styleManager = new StyleManager();
        
        // Create a TextItem with test content
        TextItem textItem = new TextItem(1, "Test Content");
        
        // Act: Call getBoundingBox() with real objects
        Rectangle boundingBox = textItem.getBoundingBox(graphics, null, 1.0f, styleManager);
        
        // Assert: Verify basic properties
        Style style = styleManager.getStyle(1);
        assertEquals((int)style.getIndent(), boundingBox.x, "X position should match style indent");
        assertEquals(0, boundingBox.y, "Y position should be 0");
        assertTrue(boundingBox.width > 0, "Width should be positive");
        assertTrue(boundingBox.height > 0, "Height should be positive");
        
        // Clean up
        graphics.dispose();
    }
    
    @Test
    void testTextItemFactoryWithInvalidInput() {
        SlideItemFactory factory = new TextItemCreator();
        
        // Test null input
        assertThrows(IllegalArgumentException.class, () -> factory.createSlideItem(1, null));
        
        // Test negative level
        assertThrows(IllegalArgumentException.class, () -> factory.createSlideItem(-1, "Invalid Level"));
    }
    
}