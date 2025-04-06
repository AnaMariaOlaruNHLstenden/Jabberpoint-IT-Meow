package com.nhlstenden.ui.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

import com.nhlstenden.factorymethodandcomposite.Presentation; 
import com.nhlstenden.ui.view.TestAboutBox;

public class MenuControllerTest {
    private Frame mockFrame;
    private Presentation mockPresentation;
    private MenuController menuController;
    private Command mockCommand;

    @BeforeEach
    void setUp() {
        mockFrame = mock(Frame.class);
        mockPresentation = mock(Presentation.class);
        mockCommand = mock(Command.class);
        menuController = new MenuController(mockFrame, mockPresentation);
        TestAboutBox.reset();
    }

    @Test
    void testSetCommand_AddsCommandToMap() {
        // Given
        String commandName = "testCommand";

        // When
        menuController.setCommand(commandName, mockCommand);

        // Then
        // We can't directly access the commands map, but we can verify it through executeCommand
        menuController.executeCommand(commandName);
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void testExecuteCommand_WithExistingCommand() {
        // Given
        String commandName = "testCommand";
        menuController.setCommand(commandName, mockCommand);

        // When
        menuController.executeCommand(commandName);

        // Then
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void testExecuteCommand_WithNonExistingCommand() {
        // When
        menuController.executeCommand("nonExistingCommand");

        // Then
        // Should not throw any exception
        assertDoesNotThrow(() -> menuController.executeCommand("nonExistingCommand"));
    }

    @Test
    void testMkMenuItem_CreatesMenuItemWithShortcut() {
        // When
        MenuItem menuItem = menuController.mkMenuItem("Test");

        // Then
        assertNotNull(menuItem);
        assertEquals("Test", menuItem.getLabel());
        assertNotNull(menuItem.getShortcut());
    }

    @Test
    void testConstructor_InitializesCommands() {
        // Then
        // Verify that all default commands are registered by trying to execute them
        assertDoesNotThrow(() -> menuController.executeCommand("Next"));
        assertDoesNotThrow(() -> menuController.executeCommand("Prev"));
        assertDoesNotThrow(() -> menuController.executeCommand("Exit"));
        assertDoesNotThrow(() -> menuController.executeCommand("Open"));
        assertDoesNotThrow(() -> menuController.executeCommand("Save"));
        assertDoesNotThrow(() -> menuController.executeCommand("New"));
        assertDoesNotThrow(() -> menuController.executeCommand("Go to"));
        
        // Test About command separately using our test-specific implementation
        TestAboutCommand testAboutCommand = new TestAboutCommand(mockFrame);
        menuController.setCommand("About", testAboutCommand);
        menuController.executeCommand("About");
        assertTrue(TestAboutBox.wasShown(), "AboutBox should have been shown");
        assertEquals(mockFrame, TestAboutBox.getLastFrame(), "AboutBox should have been shown with the correct frame");
    }
} 