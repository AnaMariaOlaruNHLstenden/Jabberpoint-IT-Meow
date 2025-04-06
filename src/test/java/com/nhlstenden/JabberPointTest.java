package com.nhlstenden;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nhlstenden.style.StyleManager;
import com.nhlstenden.ui.view.SlideViewerFrame;
import com.nhlstenden.factorymethodandcomposite.Presentation;
import com.nhlstenden.accessor.Accessor;
import com.nhlstenden.accessor.XMLAccessor;

import javax.swing.JOptionPane;
import java.io.IOException;

public class JabberPointTest {
    @Mock
    private StyleManager mockStyleManager;
    @Mock
    private Presentation mockPresentation;
    @Mock
    private SlideViewerFrame mockFrame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApplicationInitialization() {
        assertNotNull(mockStyleManager);
        assertNotNull(mockPresentation);
        assertNotNull(mockFrame);
    }
}
