import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InOrder;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideViewerComponentTest {
    @Mock
    private Presentation mockPresentation;
    @Mock
    private JFrame mockFrame;
    @Mock
    private StyleManager mockStyleManager;
    @Mock
    private Slide mockSlide;
    @Mock
    private Graphics mockGraphics;

    private SlideViewerComponent slideViewerComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        slideViewerComponent = new SlideViewerComponent(mockPresentation, mockFrame, mockStyleManager);
    }

    @Test
    void testUpdateWithValidSlide() {
        // Arrange
        when(mockPresentation.getTitle()).thenReturn("Test Presentation");

        // Act
        slideViewerComponent.update(mockPresentation, mockSlide);

        // Assert
        verify(mockFrame).setTitle("Test Presentation");
    }

    @Test
    void testUpdateWithNullSlide() {
        // Act
        slideViewerComponent.update(mockPresentation, null);

        // Assert
        verify(mockFrame, never()).setTitle(any());
    }

    @Test
    void testPaintComponentWithValidSlide() {
        // Arrange
        Vector<SlideComponent> slideItems = new Vector<>();
        when(mockPresentation.getSlideNumber()).thenReturn(0);
        when(mockPresentation.getSize()).thenReturn(5);
        when(mockSlide.getSlideItems()).thenReturn(slideItems);
        slideViewerComponent.update(mockPresentation, mockSlide);

        // Act
        slideViewerComponent.paintComponent(mockGraphics);

        // Assert
        InOrder inOrder = inOrder(mockGraphics);
        inOrder.verify(mockGraphics).setColor(eq(Color.white)); // Background color
        inOrder.verify(mockGraphics).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
        inOrder.verify(mockGraphics).setFont(any(Font.class));
        inOrder.verify(mockGraphics).setColor(eq(Color.black)); // Text color
        inOrder.verify(mockGraphics).drawString(eq("Slide 1 of 5"), anyInt(), anyInt());
    }

    @Test
    void testPaintComponentWithNoSlide() {
        // Arrange
        when(mockPresentation.getSlideNumber()).thenReturn(-1);

        // Act
        slideViewerComponent.paintComponent(mockGraphics);

        // Assert
        verify(mockGraphics, never()).drawString(anyString(), anyInt(), anyInt());
    }

    @Test
    void testGetPreferredSize() {
        // Act
        Dimension size = slideViewerComponent.getPreferredSize();

        // Assert
        assertEquals(Slide.WIDTH, size.width);
        assertEquals(Slide.HEIGHT, size.height);
    }
} 