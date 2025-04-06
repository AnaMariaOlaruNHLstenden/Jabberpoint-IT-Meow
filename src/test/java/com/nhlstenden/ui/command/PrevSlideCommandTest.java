package com.nhlstenden.ui.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.nhlstenden.factorymethodandcomposite.Presentation;

public class PrevSlideCommandTest {
    private Presentation mockPresentation;
    private PrevSlideCommand prevSlideCommand;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        prevSlideCommand = new PrevSlideCommand(mockPresentation);
    }

    @Test
    void testExecute_CallsPrevSlide() {
        // When
        prevSlideCommand.execute();

        // Then
        verify(mockPresentation, times(1)).prevSlide();
    }
} 