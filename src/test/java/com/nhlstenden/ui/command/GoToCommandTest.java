package com.nhlstenden.ui.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JOptionPane;
import java.awt.Frame;
import com.nhlstenden.factorymethodandcomposite.Presentation;

public class GoToCommandTest {
    @Mock
    private Presentation mockPresentation;
    @Mock
    private Frame mockFrame;
    
    private GoToCommand goToCommand;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        goToCommand = new GoToCommand(mockPresentation);
        goToCommand.parent = mockFrame;
    }
    
    @Test
    void testExecute_WithNoSlides_ShowsMessage() {
        // Given
        when(mockPresentation.getSize()).thenReturn(0);
        
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // When
            goToCommand.execute();
            
            // Then
            verify(mockPresentation).getSize();
            mockedStatic.verify(() ->
                JOptionPane.showMessageDialog(
                    eq(mockFrame),
                    eq("There are no slides in the presentation."),
                    eq("Empty Presentation"),
                    eq(JOptionPane.INFORMATION_MESSAGE)
                )
            );
        }
    }

    @Test
    void testExecute_WithValidInput() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Setup the input dialog to return "3"
            mockedStatic.when(() -> JOptionPane.showInputDialog(
                eq(mockFrame),
                eq("Enter slide number (1 - 5):"),
                eq("Go To Slide"),
                eq(JOptionPane.QUESTION_MESSAGE)
            )).thenReturn("3");
            
            // When
            goToCommand.execute();
            
            // Then
            verify(mockPresentation).getSize();
            verify(mockPresentation).setSlideNumber(2); // 3-1 because slide numbers are 0-based
        }
    }

    @Test
    void testExecute_WithCancelledInput() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Setup the input dialog to return null (simulating cancel)
            mockedStatic.when(() -> JOptionPane.showInputDialog(
                eq(mockFrame),
                eq("Enter slide number (1 - 5):"),
                eq("Go To Slide"),
                eq(JOptionPane.QUESTION_MESSAGE)
            )).thenReturn(null);
            
            // When
            goToCommand.execute();
            
            // Then
            verify(mockPresentation).getSize();
            verify(mockPresentation, never()).setSlideNumber(anyInt());
        }
    }
}