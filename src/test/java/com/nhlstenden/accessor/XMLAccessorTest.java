package com.nhlstenden.accessor;

import com.nhlstenden.factorymethodandcomposite.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest {
    private XMLAccessor xmlAccessor;
    private Presentation presentation;
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() {
        xmlAccessor = new XMLAccessor();
        presentation = new Presentation();
    }
    
    @Test
    void testLoadNonExistentFile() throws IOException {
        Presentation presentation = new Presentation();
        // Add some initial content to verify it remains unchanged
        presentation.setTitle("Initial Title");
        presentation.append(new Slide());
        
        // Try to load non-existent file
        xmlAccessor.loadFile(presentation, "nonexistent.xml");
        
        // Verify presentation remains unchanged
        assertEquals("Initial Title", presentation.getTitle());
        assertEquals(1, presentation.getSize());
    }
} 