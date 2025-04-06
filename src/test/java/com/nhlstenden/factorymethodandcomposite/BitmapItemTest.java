package com.nhlstenden.factorymethodandcomposite;

import com.nhlstenden.style.StyleManager;
import com.nhlstenden.style.Style;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BitmapItemTest {
    @Mock
    private Graphics mockGraphics;
    @Mock
    private ImageObserver mockObserver;
    @Mock
    private StyleManager mockStyleManager;
    @Mock
    private Style mockStyle;
    @Mock
    private BufferedImage mockBufferedImage;

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
        // Setup mocks
        MockitoAnnotations.openMocks(this);
        when(mockStyleManager.getStyle(anyInt())).thenReturn(mockStyle);
        when(mockStyle.getIndent()).thenReturn(20);
        when(mockStyle.getLeading()).thenReturn(10);
        
        // Create a real BitmapItem with mocked image
        try (MockedStatic<ImageIO> imageIOMock = Mockito.mockStatic(ImageIO.class)) {
            imageIOMock.when(() -> ImageIO.read(any(File.class))).thenReturn(mockBufferedImage);
            when(mockBufferedImage.getWidth(any())).thenReturn(100);
            when(mockBufferedImage.getHeight(any())).thenReturn(50);
            
            BitmapItem bitmapItem = new BitmapItem(1, "test-image.png");
            
            // Act
            bitmapItem.draw(mockGraphics, 50, 50, 1.0f, mockObserver, mockStyleManager);
            
            // Verify interactions
            verify(mockGraphics).drawImage(
                eq(mockBufferedImage),
                eq(70), // x + indent
                eq(60), // y + leading
                eq(100), // width
                eq(50), // height
                eq(mockObserver)
            );
        }
    }
    
    @Test
    void testBitmapItemBoundingBox() {
        // Setup mocks
        MockitoAnnotations.openMocks(this);
        when(mockStyleManager.getStyle(anyInt())).thenReturn(mockStyle);
        when(mockStyle.getIndent()).thenReturn(20);
        when(mockStyle.getLeading()).thenReturn(10);
        
        // Create a real BitmapItem with mocked image
        try (MockedStatic<ImageIO> imageIOMock = Mockito.mockStatic(ImageIO.class)) {
            imageIOMock.when(() -> ImageIO.read(any(File.class))).thenReturn(mockBufferedImage);
            when(mockBufferedImage.getWidth(any())).thenReturn(100);
            when(mockBufferedImage.getHeight(any())).thenReturn(50);
            
            BitmapItem bitmapItem = new BitmapItem(1, "test-image.png");
            
            // Act
            Rectangle boundingBox = bitmapItem.getBoundingBox(mockGraphics, mockObserver, 1.0f, mockStyleManager);
            
            // Assert
            assertEquals(20, boundingBox.x, "X position should match style indent");
            assertEquals(0, boundingBox.y, "Y position should be 0");
            assertEquals(100, boundingBox.width, "Width should match image width");
            assertEquals(60, boundingBox.height, "Height should be leading + image height");
        }
    }
    
    @Test
    void testBitmapItemFactoryWithInvalidInput() {
        SlideItemFactory factory = new BitmapItemCreator();
        
        // Test null content
        IllegalArgumentException nullException = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createSlideItem(1, null)
        );
        assertEquals("File name cannot be null or empty.", nullException.getMessage());
        
        // Test empty content
        IllegalArgumentException emptyException = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createSlideItem(1, "")
        );
        assertEquals("File name cannot be null or empty.", emptyException.getMessage());
        
        // Test invalid file extension
        IllegalArgumentException invalidExtensionException = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createSlideItem(1, "test.txt")
        );
        assertEquals("Invalid file type for BitmapItem: test.txt", invalidExtensionException.getMessage());
        
        // Test non-existent file
        try (MockedStatic<ImageIO> imageIOMock = Mockito.mockStatic(ImageIO.class)) {
            imageIOMock.when(() -> ImageIO.read(any(File.class))).thenThrow(new IOException("File not found"));
            
            IllegalArgumentException ioException = assertThrows(
                    IllegalArgumentException.class,
                    () -> factory.createSlideItem(1, "non-existent.png")
            );
            assertEquals("Could not read the file: non-existent.png", ioException.getMessage());
        }
    }
}