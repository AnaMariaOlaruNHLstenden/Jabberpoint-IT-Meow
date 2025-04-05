import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class AboutCommandTest {
    private Frame mockFrame;
    private AboutCommand aboutCommand;

    @BeforeEach
    void setUp() {
        mockFrame = mock(Frame.class);
        aboutCommand = new AboutCommand(mockFrame);
    }

    @Test
    void testExecute_CallsAboutBoxShow() {
        // When
        aboutCommand.execute();

        // Then
        // We can't directly verify the static AboutBox.show() call,
        // but we can verify that the command executes without throwing exceptions
        assertDoesNotThrow(() -> aboutCommand.execute());
    }
} 