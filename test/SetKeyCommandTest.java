import Command.Command;
import FactoryMethodAndComposite.Presentation;
import Command.KeyController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Frame;
import java.awt.event.KeyEvent;

class KeyControllerTest {
    private KeyController keyController;
    private Presentation mockPresentation;
    private Frame mockFrame;
    private Command mockCommand;
    
    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(Frame.class);
        mockCommand = mock(Command.class);
        keyController = new KeyController(mockPresentation);
    }
    
    @Test
    void testSetKeyCommand_Success() {
        // Given
        Command newMockCommand = mock(Command.class);
        
        // When
        keyController.setKeyCommand(KeyEvent.VK_T, newMockCommand);
        
        // Then
        // Simulate key press to verify the command was registered
        KeyEvent mockKeyEvent = new KeyEvent(mockFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_T, 'T');
        keyController.keyPressed(mockKeyEvent);
        verify(newMockCommand, times(1)).execute();
    }
    
    @Test
    void testSetKeyCommand_DuplicateKey() {
        // Given
        Command firstCommand = mock(Command.class);
        Command secondCommand = mock(Command.class);
        
        // When/Then
        keyController.setKeyCommand(KeyEvent.VK_T, firstCommand);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            keyController.setKeyCommand(KeyEvent.VK_T, secondCommand);
        });
        String expectedMessage = "Key code " + KeyEvent.VK_T + " is already assigned to another command.";
        assertEquals(expectedMessage, thrown.getMessage());
    }
    
    @Test
    void testDefaultKeyBindings() {
        // Test some default key bindings
        KeyEvent nextKeyEvent = new KeyEvent(mockFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_DOWN, (char) KeyEvent.VK_PAGE_DOWN);
        keyController.keyPressed(nextKeyEvent);
        // The Command.NextSlideCommand should have been executed on the presentation
        verify(mockPresentation, times(1)).nextSlide();
        
        KeyEvent prevKeyEvent = new KeyEvent(mockFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_UP, (char) KeyEvent.VK_PAGE_UP);
        keyController.keyPressed(prevKeyEvent);
        // The Command.PrevSlideCommand should have been executed on the presentation
        verify(mockPresentation, times(1)).prevSlide();
    }
    
    @Test
    void testKeyPressed_UnregisteredKey() {
        // Given
        KeyEvent mockKeyEvent = new KeyEvent(mockFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_F1, 'F');
        
        // When
        keyController.keyPressed(mockKeyEvent);
        
        // Then
        // No commands should be executed
        verifyNoInteractions(mockCommand);
    }
    
    @Test
    void testMultipleKeysForSameCommand() {
        // Test that multiple keys can trigger the next slide command
        KeyEvent[] nextSlideEvents = {
                new KeyEvent(mockFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_DOWN, (char) KeyEvent.VK_PAGE_DOWN),
                new KeyEvent(mockFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, (char) KeyEvent.VK_DOWN),
                new KeyEvent(mockFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, (char) KeyEvent.VK_ENTER),
                new KeyEvent(mockFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PLUS, '+')
        };
        
        // Each key should trigger nextSlide
        for (KeyEvent event : nextSlideEvents) {
            keyController.keyPressed(event);
        }
        
        // Verify nextSlide was called once for each key
        verify(mockPresentation, times(4)).nextSlide();
    }
}