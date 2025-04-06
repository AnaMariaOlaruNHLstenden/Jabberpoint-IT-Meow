package com.nhlstenden.ui.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import com.nhlstenden.factorymethodandcomposite.Presentation;

public class NextSlideCommandTest {
    private Presentation mockPresentation;
    private NextSlideCommand nextSlideCommand;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        nextSlideCommand = new NextSlideCommand(mockPresentation);
    }

    @Test
    void testExecute_CallsNextSlide() {
        // When
        nextSlideCommand.execute();

        // Then
        verify(mockPresentation, times(1)).nextSlide();
    }
} 