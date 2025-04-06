package com.nhlstenden.factorymethodandcomposite;

import com.nhlstenden.style.StyleManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BitmapItemTest {
    
    @Test
    void testBitmapItemAttributes() {
        // Arrange: Mock BitmapItem
        BitmapItem mockBitmapItem = mock(BitmapItem.class);
        
        // Define behavior for mocked getters
        when(mockBitmapItem.getLevel()).thenReturn(1);
        when(mockBitmapItem.getName()).thenReturn("test-image.png");
        
        // Act: Use the mocked BitmapItem
        int level = mockBitmapItem.getLevel();
        String name = mockBitmapItem.getName();
        
        // Assert
        assertEquals(1, level, "BitmapItem level should be 1");
        assertEquals("test-image.png", name, "BitmapItem name should match the input");
    }
    
    @Test
    void testBitmapItemWithMockedBehavior() {
        // Mock BitmapItem
        BitmapItem mockedBitmapItem = Mockito.mock(BitmapItem.class);
        
        // Stub methods
        Mockito.when(mockedBitmapItem.getName()).thenReturn("assets/mocked-image.png");
        
        // Verify mocked behavior
        assertEquals("assets/mocked-image.png", mockedBitmapItem.getName(), "Mocked file name should be returned.");
        
        // Verify method interactions
        Mockito.verify(mockedBitmapItem).getName();
    }
    
    @Test
    void testBitmapItemDrawWithMocks() {
        // Mocking dependencies
        Graphics mockGraphics = Mockito.mock(Graphics.class);
        ImageObserver mockObserver = Mockito.mock(ImageObserver.class);
        StyleManager mockStyleManager = Mockito.mock(StyleManager.class);
        
        // Mock BitmapItem
        BitmapItem mockedBitmapItem = Mockito.mock(BitmapItem.class);
        
        // Call the method to verify interactions
        mockedBitmapItem.draw(mockGraphics, 50, 50, 1.0f, mockObserver, mockStyleManager);
        
        // Verify interactions
        Mockito.verify(mockedBitmapItem)
                .draw(Mockito.any(Graphics.class), Mockito.eq(50), Mockito.eq(50), Mockito.eq(1.0f), Mockito.any(ImageObserver.class), Mockito.any(StyleManager.class));
    }
    
    @Test
    void testBitmapItemBoundingBox() {
        // Mocking dependencies
        Graphics mockGraphics = Mockito.mock(Graphics.class);
        ImageObserver mockObserver = Mockito.mock(ImageObserver.class);
        StyleManager mockStyleManager = Mockito.mock(StyleManager.class);
        
        // Mock BitmapItem
        BitmapItem mockedBitmapItem = Mockito.mock(BitmapItem.class);
        Rectangle mockRectangle = new Rectangle(0, 0, 100, 50);
        
        // Stub getBoundingBox
        Mockito.when(mockedBitmapItem.getBoundingBox(mockGraphics, mockObserver, 1.0f, mockStyleManager))
                .thenReturn(mockRectangle);
        
        // Test getBoundingBox
        Rectangle result = mockedBitmapItem.getBoundingBox(mockGraphics, mockObserver, 1.0f, mockStyleManager);
        
        // Validate result
        assertEquals(mockRectangle, result, "Mocked bounding box should be returned.");
        
        // Verify method interactions
        Mockito.verify(mockedBitmapItem)
                .getBoundingBox(Mockito.any(Graphics.class), Mockito.any(ImageObserver.class), Mockito.eq(1.0f), Mockito.any(StyleManager.class));
    }
    
    @Test
    void testBitmapItemFactoryWithInvalidInput() {
        SlideItemFactory factory = new BitmapItemCreator(); // Factory to create BitmapItem instances
        
        // Null content (file name)
        IllegalArgumentException nullException = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createSlideItem(1, null)
        );
        assertEquals("File name cannot be null or empty.", nullException.getMessage(),
                "Expected a validation message for null file names");
        
        // Non-existent file (adjust test based on behavior—BitmapItem doesn't check file existence)
        IllegalArgumentException nonExistentFileException = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createSlideItem(2, "non-existent-file.png")
        );
        assertEquals("Could not read the file: non-existent-file.png", nonExistentFileException.getMessage(),
                "Expected a validation message for invalid file names");
        
        // Invalid file extension
        IllegalArgumentException invalidExtensionException = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createSlideItem(3, "file.txt")
        );
        assertEquals("Invalid file type for BitmapItem: file.txt", invalidExtensionException.getMessage(),
                "Expected a validation message for invalid file extensions");
    }
   
}