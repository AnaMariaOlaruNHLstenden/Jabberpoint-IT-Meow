package com.nhlstenden.accessor;

import com.nhlstenden.app.DemoPresentation;
import com.nhlstenden.factorymethodandcomposite.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class AccessorTest {
    private Accessor accessor;
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        accessor = new DemoPresentation();
        presentation = new Presentation();
    }

    @Test
    void testGetDemoAccessor() {
        Accessor demoAccessor = Accessor.getDemoAccessor();
        assertNotNull(demoAccessor);
        assertTrue(demoAccessor instanceof DemoPresentation);
    }

    @Test
    void testLoadFile() throws IOException {
        // Test loading a demo presentation
        accessor.loadFile(presentation, "dummy");
        
        // Verify the presentation was loaded correctly
        assertEquals("Demo Presentation", presentation.getTitle());
        assertEquals(3, presentation.getSize());
        
        // Verify first slide
        assertEquals("JabberPoint", presentation.getSlide(0).getTitle());
        assertEquals(10, presentation.getSlide(0).getSize());
        
        // Verify second slide
        assertEquals("Demonstration of levels and styles", presentation.getSlide(1).getTitle());
        assertEquals(7, presentation.getSlide(1).getSize());
        
        // Verify third slide
        assertEquals("The third slide", presentation.getSlide(2).getTitle());
        assertEquals(5, presentation.getSlide(2).getSize());
    }

    @Test
    void testSaveFile() {
        // Test that saving throws IllegalStateException as expected
        assertThrows(IllegalStateException.class, () -> {
            accessor.saveFile(presentation, "dummy");
        });
    }

    @Test
    void testConstants() {
        assertEquals("Demonstration presentation", Accessor.DEMO_NAME);
        assertEquals(".xml", Accessor.DEFAULT_EXTENSION);
    }
} 