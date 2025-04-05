import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JOptionPane;
import java.awt.Frame;

public class GoToCommandTest {
    private Presentation mockPresentation;
    private GoToCommand goToCommand;
    private Frame mockFrame;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        mockFrame = mock(Frame.class);
        goToCommand = new GoToCommand(mockPresentation);
        goToCommand.parent = mockFrame;
    }

    @Test
    void testExecute_WithNoSlides_ShowsMessage() {
        // Given
        when(mockPresentation.getSize()).thenReturn(0);

        // When
        goToCommand.execute();

        // Then
        verify(mockPresentation, times(1)).getSize();
        verify(mockFrame, times(1)).setVisible(true);
    }

    @Test
    void testExecute_WithValidSlideNumber_SetsSlideNumber() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        when(JOptionPane.showInputDialog(
                any(Frame.class),
                anyString(),
                anyString(),
                anyInt()
        )).thenReturn("3");

        // When
        goToCommand.execute();

        // Then
        verify(mockPresentation, times(1)).setSlideNumber(2); // 3-1 because of 0-based index
    }

    @Test
    void testExecute_WithInvalidSlideNumber_ShowsError() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        when(JOptionPane.showInputDialog(
                any(Frame.class),
                anyString(),
                anyString(),
                anyInt()
        )).thenReturn("6");

        // When
        goToCommand.execute();

        // Then
        verify(mockPresentation, never()).setSlideNumber(anyInt());
        verify(mockFrame, times(1)).setVisible(true);
    }

    @Test
    void testExecute_WithNonNumericInput_ShowsError() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        when(JOptionPane.showInputDialog(
                any(Frame.class),
                anyString(),
                anyString(),
                anyInt()
        )).thenReturn("abc");

        // When
        goToCommand.execute();

        // Then
        verify(mockPresentation, never()).setSlideNumber(anyInt());
        verify(mockFrame, times(1)).setVisible(true);
    }
} 