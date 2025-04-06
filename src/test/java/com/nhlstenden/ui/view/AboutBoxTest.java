package com.nhlstenden.ui.view;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.JOptionPane;
import java.awt.Frame;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;


class AboutBoxTest {

    @Test
    void show_ShouldDisplayCorrectMessage() {
        // Arrange
        Frame mockFrame = mock(Frame.class);
        String expectedMessage = "JabberPoint is a primitive slide-show program in Java(tm). It\n" +
                "is freely copyable as long as you keep this notice and\n" +
                "the splash screen intact.\n" +
                "Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.\n" +
                "Adapted by Gert Florijn (version 1.1) and " +
                "Sylvia Stuurman (version 1.2 and higher) for the Open" +
                "University of the Netherlands, 2002 -- now." +
                "Author's version available from http://www.darwinsys.com/";

        // Act & Assert
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            AboutBox.show(mockFrame);

            // Verify that showMessageDialog was called with the correct parameters
            mockedJOptionPane.verify(() -> 
                JOptionPane.showMessageDialog(
                    eq(mockFrame),
                    eq(expectedMessage),
                    eq("About JabberPoint"),
                    eq(JOptionPane.INFORMATION_MESSAGE)
                )
            );
        }
    }
} 