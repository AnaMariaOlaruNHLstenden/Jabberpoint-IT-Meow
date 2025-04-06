package com.nhlstenden.ui.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.nhlstenden.factorymethodandcomposite.Presentation;
import com.nhlstenden.style.StyleManager;
import com.nhlstenden.test.TestConfiguration;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideViewerFrameTest extends TestConfiguration {
    @Mock
    private Presentation mockPresentation;
    @Mock
    private StyleManager mockStyleManager;

    private SlideViewerFrame frame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        frame = new SlideViewerFrame("Test Title", mockPresentation, mockStyleManager);
    }

    @Test
    void testFrameInitialization() {
        // Assert
        assertEquals("Jabberpoint 1.6 - OU", frame.getTitle());
        assertTrue(frame.isVisible());
        assertEquals(new Dimension(SlideViewerFrame.WIDTH, SlideViewerFrame.HEIGHT), frame.getSize());
    }

    @Test
    void testComponentsAreAdded() {
        // Assert
        Component[] components = frame.getContentPane().getComponents();
        assertTrue(components.length > 0);
        assertTrue(components[0] instanceof SlideViewerComponent);
    }

    @Test
    void testKeyListenerIsAdded() {
        // Assert
        assertTrue(frame.getKeyListeners().length > 0);
    }

    @Test
    void testMenuBarIsSet() {
        // Assert
        assertNotNull(frame.getMenuBar());
    }

    @Test
    void testPresentationShowViewIsSet() {
        // Verify that the presentation's show view was set with a SlideViewerComponent
        verify(mockPresentation).setShowView(any(SlideViewerComponent.class));
    }
} 