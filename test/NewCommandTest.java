import Command.NewCommand;
import FactoryMethodAndComposite.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.awt.Frame;

public class NewCommandTest {
    private Presentation mockPresentation;
    private Frame mockFrame;
    private NewCommand newCommand;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(Frame.class);
        newCommand = new NewCommand(mockPresentation, mockFrame);
    }

    @Test
    void testExecute_CallsClearAndRepaint() {
        // When
        newCommand.execute();

        // Then
        verify(mockPresentation, times(1)).clear();
        verify(mockFrame, times(1)).repaint();
    }
} 