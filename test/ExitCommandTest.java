import Command.ExitCommand;
import FactoryMethodAndComposite.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.awt.Frame;

public class ExitCommandTest {
    private Presentation mockPresentation;
    private Frame mockFrame;
    private ExitCommand exitCommand;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(Frame.class);
        exitCommand = new ExitCommand(mockPresentation, mockFrame);
    }

    @Test
    void testExecute_CallsExit() {
        // When
        exitCommand.execute();

        // Then
        verify(mockPresentation, times(1)).exit(0);
    }
} 