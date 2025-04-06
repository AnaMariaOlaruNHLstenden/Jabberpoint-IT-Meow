package com.nhlstenden.ui.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Frame;
import com.nhlstenden.ui.view.AboutBox;

public class AboutCommandTest {
    @Mock
    private Frame mockFrame;
    
    private AboutCommand aboutCommand;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aboutCommand = new AboutCommand(mockFrame);
    }
    
    @Test
    void testExecute_ShowsAboutDialog() {
        try (MockedStatic<AboutBox> mockedAboutBox = mockStatic(AboutBox.class)) {
            // When
            aboutCommand.execute();
            
            // Then
            mockedAboutBox.verify(() -> AboutBox.show(mockFrame));
        }
    }
}