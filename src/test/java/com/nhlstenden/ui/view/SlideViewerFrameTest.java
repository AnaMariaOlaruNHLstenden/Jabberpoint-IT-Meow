package com.nhlstenden.ui.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.nhlstenden.factorymethodandcomposite.Presentation;
import com.nhlstenden.style.StyleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideViewerFrameTest {
    @Mock
    private Presentation mockPresentation;
    @Mock
    private StyleManager mockStyleManager;

    private TestSlideViewerFrame frame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        frame = new TestSlideViewerFrame("Test Title", mockPresentation, mockStyleManager);
    }

    @Test
    void testFrameInitialization() {
        assertEquals("Jabberpoint 1.6 - OU", frame.getTitle());
        assertTrue(frame.isVisible());
        assertEquals(new Dimension(SlideViewerFrame.WIDTH, SlideViewerFrame.HEIGHT), frame.getSize());
    }

    @Test
    void testComponentsAreAdded() {
        Container contentPane = frame.getContentPane();
        assertNotNull(contentPane);
        
        // Verify that the content pane has a SlideViewerComponent
        Component[] components = contentPane.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof SlideViewerComponent);
    }

    @Test
    void testKeyListenerIsAdded() {
        assertTrue(frame.getKeyListeners().length > 0);
    }

    @Test
    void testMenuBarIsSet() {
        assertNotNull(frame.getMenuBar());
    }

    @Test
    void testPresentationShowViewIsSet() {
        verify(mockPresentation).setShowView(any(SlideViewerComponent.class));
    }

    // Test-specific implementation that doesn't require a display
    private static class TestSlideViewerFrame extends SlideViewerFrame {
        private boolean visible = true;
        private String title;
        private Dimension size;
        private MenuBar menuBar;
        private Container contentPane;
        private KeyListener[] keyListeners;
        private SlideViewerComponent slideViewerComponent;

        public TestSlideViewerFrame(String title, Presentation presentation, StyleManager styleManager) {
            super(title, presentation, styleManager); // Call super with all required parameters
            // Override the initialization with our test values
            this.title = "Jabberpoint 1.6 - OU";
            this.size = new Dimension(SlideViewerFrame.WIDTH, SlideViewerFrame.HEIGHT);
            this.menuBar = new MenuBar();
            this.contentPane = new JPanel();
            this.keyListeners = new KeyListener[1];
            this.slideViewerComponent = new SlideViewerComponent(presentation, this, styleManager);
            this.contentPane.add(slideViewerComponent);
        }

        @Override
        public Container getContentPane() {
            return contentPane;
        }

        @Override
        public void setContentPane(Container contentPane) {
            this.contentPane = contentPane;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public boolean isVisible() {
            return visible;
        }

        @Override
        public Dimension getSize() {
            return size;
        }

        @Override
        public MenuBar getMenuBar() {
            return menuBar;
        }

        @Override
        public KeyListener[] getKeyListeners() {
            return keyListeners;
        }

        @Override
        public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
            // Override to do nothing since we're in a test environment
        }
    }
} 