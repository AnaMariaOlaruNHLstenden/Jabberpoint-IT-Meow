import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class AboutCommandTest {
    private Frame mockFrame;
    private TestAboutCommand aboutCommand;
    
    @BeforeEach
    void setUp() {
        mockFrame = mock(Frame.class);
        aboutCommand = new TestAboutCommand(mockFrame);
        TestAboutBox.reset();
    }
    
    @Test
    void testExecute_CallsAboutBoxShow() {
        // When
        aboutCommand.execute();
        
        // Then
        assertTrue(TestAboutBox.wasShown(), "App.AboutBox should have been shown");
        assertEquals(mockFrame, TestAboutBox.getLastFrame(), "App.AboutBox should have been shown with the correct frame");
    }
}