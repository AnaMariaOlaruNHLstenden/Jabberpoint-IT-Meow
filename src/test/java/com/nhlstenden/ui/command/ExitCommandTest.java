package com.nhlstenden.ui.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Frame;
import com.nhlstenden.factorymethodandcomposite.Presentation;

public class ExitCommandTest {
    private Presentation mockPresentation;
    private Frame mockFrame;
    private ExitCommand exitCommand;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(Frame.class);
        exitCommand = new ExitCommand(mockPresentation, mockFrame);
    }

    @Test
    void testExecute_CallsExit() {
        // When
        exitCommand.execute();

        // Then
        verify(mockPresentation, times(1)).exit(0);
    }
} 