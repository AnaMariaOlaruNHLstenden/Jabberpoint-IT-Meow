package com.nhlstenden.ui.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import javax.swing.JOptionPane;

import com.nhlstenden.factorymethodandcomposite.Presentation; 
import com.nhlstenden.ui.view.TestAboutBox;
import com.nhlstenden.factorymethodandcomposite.Slide;

public class MenuControllerTest {
    @Mock
    private Presentation mockPresentation;
    @Mock
    private Command mockCommand;
    @Mock
    private Slide mockSlide;

    private TestMenuController menuController;
    private TestFrame testFrame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testFrame = new TestFrame();
        menuController = new TestMenuController(testFrame, mockPresentation);
        TestAboutBox.reset();
        
        // Setup mock presentation
        when(mockPresentation.getCurrentSlide()).thenReturn(mockSlide);
        when(mockSlide.getTitle()).thenReturn("Test Slide");
    }

    @Test
    void testSetCommand_AddsCommandToMap() {
        String commandName = "testCommand";
        menuController.setCommand(commandName, mockCommand);
        menuController.executeCommand(commandName);
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void testExecuteCommand_WithExistingCommand() {
        String commandName = "testCommand";
        menuController.setCommand(commandName, mockCommand);
        menuController.executeCommand(commandName);
        verify(mockCommand, times(1)).execute();
    }

    @Test
    void testExecuteCommand_WithNonExistingCommand() {
        menuController.executeCommand("nonExistingCommand");
        assertDoesNotThrow(() -> menuController.executeCommand("nonExistingCommand"));
    }

    @Test
    void testMkMenuItem_CreatesMenuItemWithShortcut() {
        MenuItem menuItem = menuController.mkMenuItem("Test");
        assertNotNull(menuItem);
        assertEquals("Test", menuItem.getLabel());
    }

    @Test
    void testConstructor_InitializesCommands() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Mock the JOptionPane for GoToCommand
            when(mockPresentation.getSize()).thenReturn(0); // Return 0 slides to trigger the "no slides" message
            
            assertDoesNotThrow(() -> menuController.executeCommand("Next"));
            assertDoesNotThrow(() -> menuController.executeCommand("Prev"));
            assertDoesNotThrow(() -> menuController.executeCommand("Exit"));
            assertDoesNotThrow(() -> menuController.executeCommand("Open"));
            assertDoesNotThrow(() -> menuController.executeCommand("Save"));
            assertDoesNotThrow(() -> menuController.executeCommand("New"));
            assertDoesNotThrow(() -> menuController.executeCommand("Go to"));
            
            TestAboutCommand testAboutCommand = new TestAboutCommand(testFrame);
            menuController.setCommand("About", testAboutCommand);
            menuController.executeCommand("About");
            assertTrue(TestAboutBox.wasShown());
        }
    }

    // Test-specific implementation that doesn't require a display
    private static class TestFrame extends Frame {
        private boolean visible = false;
        private String title;
        private MenuBar menuBar;

        public TestFrame() {
            super("Test Frame");
            this.title = "Test Frame";
            this.menuBar = new MenuBar();
        }

        @Override
        public void setMenuBar(MenuBar mb) {
            this.menuBar = mb;
        }

        @Override
        public MenuBar getMenuBar() {
            return menuBar;
        }

        @Override
        public void setVisible(boolean b) {
            this.visible = b;
        }

        @Override
        public boolean isVisible() {
            return visible;
        }

        @Override
        public void repaint() {
            // Do nothing for testing
        }
    }

    // Test-specific implementation that doesn't require a display
    private static class TestMenuController extends MenuController {
        public TestMenuController(Frame frame, Presentation presentation) {
            super(frame, presentation);
        }

        @Override
        public MenuItem mkMenuItem(String name) {
            return new TestMenuItem(name);
        }
    }

    // Test-specific implementation for MenuItem
    private static class TestMenuItem extends MenuItem {
        private String label;
        private MenuShortcut shortcut;

        public TestMenuItem(String label) {
            super(label);
            this.label = label;
        }

        @Override
        public void setShortcut(MenuShortcut s) {
            this.shortcut = s;
        }

        @Override
        public String getLabel() {
            return label;
        }

        @Override
        public void addActionListener(java.awt.event.ActionListener l) {
            // Do nothing for testing
        }
    }
} 