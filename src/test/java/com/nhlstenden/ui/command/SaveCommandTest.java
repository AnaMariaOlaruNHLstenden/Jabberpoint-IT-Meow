package com.nhlstenden.ui.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.io.IOException;
import com.nhlstenden.factorymethodandcomposite.Presentation;
import com.nhlstenden.accessor.XMLAccessor;


public class SaveCommandTest {
    private Presentation mockPresentation;
    private Frame mockFrame;
    private XMLAccessor mockXmlAccessor;
    private SaveCommand saveCommand;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(Frame.class);
        mockXmlAccessor = mock(XMLAccessor.class);
        saveCommand = new SaveCommand(mockPresentation, mockFrame);
    }

    @Test
    void testExecute_SuccessfulSave() throws IOException {
        // Given
        doNothing().when(mockXmlAccessor).saveFile(any(Presentation.class), anyString());

        // When
        saveCommand.execute();

        // Then
        verify(mockFrame, times(1)).repaint();
    }

    @Test
    void testExecute_IOException() throws IOException {
        // Given
        IOException ioException = new IOException("Test exception");
        doThrow(ioException).when(mockXmlAccessor).saveFile(any(Presentation.class), anyString());

        // When
        saveCommand.execute();

        // Then
        verify(mockFrame, times(1)).repaint();
    }
} 