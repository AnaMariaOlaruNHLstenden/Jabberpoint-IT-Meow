import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.io.IOException;

public class OpenCommandTest {
    private Presentation mockPresentation;
    private Frame mockFrame;
    private XMLAccessor mockXmlAccessor;
    private OpenCommand openCommand;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(Frame.class);
        mockXmlAccessor = mock(XMLAccessor.class);
        openCommand = new OpenCommand(mockPresentation, mockFrame);
    }

    @Test
    void testExecute_SuccessfulLoad() throws IOException {
        // Given
        doNothing().when(mockXmlAccessor).loadFile(any(Presentation.class), anyString());

        // When
        openCommand.execute();

        // Then
        verify(mockPresentation, times(1)).clear();
        verify(mockPresentation, times(1)).setSlideNumber(0);
        verify(mockFrame, times(1)).repaint();
    }

    @Test
    void testExecute_IOException() throws IOException {
        // Given
        IOException ioException = new IOException("Test exception");
        doThrow(ioException).when(mockXmlAccessor).loadFile(any(Presentation.class), anyString());

        // When
        openCommand.execute();

        // Then
        verify(mockPresentation, times(1)).clear();
        verify(mockFrame, times(1)).repaint();
        verify(mockFrame, times(1)).setVisible(true);
    }
} 