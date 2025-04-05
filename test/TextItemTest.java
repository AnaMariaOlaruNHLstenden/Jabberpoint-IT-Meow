import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
        BufferedImage canvas = new BufferedImage(200, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = canvas.createGraphics();
        graphics.setFont(new Font("Arial", Font.PLAIN, 12));
        graphics.setColor(Color.BLACK);
        
        // Create a TextItem instance
        TextItem textItem = new TextItem(1, "Hello, World!");
        StyleManager styleManager = new StyleManager();
        
        // Call the draw method
        textItem.draw(graphics, 50, 50, 1.0f, null, styleManager);
        
        // Validate expected output indirectly by checking pixel colors (logic-based approximation)
        Color pixelColor = new Color(canvas.getRGB(50, 50), true);
        // Ensure the text drawing has affected the canvas (not blank or unmodified pixels)
        assertNotEquals(new Color(0, 0, 0, 0), pixelColor, "The pixel should not be blank after drawing");
        
        // Clean up resources
        graphics.dispose();
    }
    
    @Test
    void testTextItemBoundingBox() {
        // Mock dependencies
        Graphics mockGraphics = mock(Graphics.class);
        StyleManager styleManager = new StyleManager();
        
        // Mock the FontMetrics to simulate text measurement
        FontMetrics mockFontMetrics = mock(FontMetrics.class);
        when(mockGraphics.getFontMetrics(any())).thenReturn(mockFontMetrics);
        when(mockFontMetrics.stringWidth("Test Content")).thenReturn(100); // Simulated width
        when(mockFontMetrics.getHeight()).thenReturn(20); // Simulated height
        
        // Create a TextItem with test content
        TextItem textItem = new TextItem(1, "Test Content");
        
        // Act: Call getBoundingBox()
        Rectangle boundingBox = textItem.getBoundingBox(mockGraphics, null, 1.0f, styleManager);
        
        // Assert: Verify the bounding box dimensions
        assertEquals(100, boundingBox.width, "Width should match text width.");
        assertEquals(20, boundingBox.height, "Height should match text height.");
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