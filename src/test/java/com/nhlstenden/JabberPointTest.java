package com.nhlstenden;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.nhlstenden.style.StyleManager;
import com.nhlstenden.ui.view.SlideViewerFrame;
import com.nhlstenden.factorymethodandcomposite.Presentation;
import com.nhlstenden.accessor.Accessor;
import com.nhlstenden.accessor.XMLAccessor;
import com.nhlstenden.test.BaseGuiTest;

import javax.swing.JOptionPane;
import java.io.IOException;

public class JabberPointTest extends BaseGuiTest {
    private StyleManager styleManager;
    private Presentation presentation;
    private SlideViewerFrame frame;

    @BeforeEach
    void setUp() {
        System.setProperty("java.awt.headless", "true");
        styleManager = new StyleManager();
        presentation = new Presentation();
        frame = new SlideViewerFrame("Test", presentation, styleManager);
    }

    @Test
    void testApplicationInitialization() {
        assertNotNull(styleManager);
        assertNotNull(presentation);
        assertNotNull(frame);
    }

    @Test
    void testMainWithNoArguments() throws IOException {
        Accessor mockAccessor = mock(Accessor.class);
        try (MockedStatic<Accessor> mockedAccessor = Mockito.mockStatic(Accessor.class)) {
            mockedAccessor.when(Accessor::getDemoAccessor).thenReturn(mockAccessor);
            try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
                JabberPoint.main(new String[]{});
                mockedAccessor.verify(Accessor::getDemoAccessor);
                verify(mockAccessor).loadFile(any(Presentation.class), eq(""));
            }
        }
    }

    @Test
    void testMainWithIOException() throws IOException {
        Accessor mockAccessor = mock(Accessor.class);
        try (MockedStatic<Accessor> mockedAccessor = Mockito.mockStatic(Accessor.class)) {
            doThrow(new IOException("Test error")).when(mockAccessor).loadFile(any(), any());
            mockedAccessor.when(Accessor::getDemoAccessor).thenReturn(mockAccessor);

            try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
                JabberPoint.main(new String[]{});
                
                // Verify JOptionPane was called with any message containing the error
                mockedJOptionPane.verify(() -> 
                    JOptionPane.showMessageDialog(
                        any(),
                        contains("Test error"),
                        anyString(),
                        eq(JOptionPane.ERROR_MESSAGE)
                    )
                );
            }
        }
    }
}
