import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
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
        
        // Use MockedStatic for JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // When
            goToCommand.execute();
            
            // Then
            verify(mockPresentation, times(1)).getSize();
            mockedStatic.verify(() ->
                    JOptionPane.showMessageDialog(
                            eq(mockFrame),
                            eq("There are no slides in the presentation."),
                            eq("Empty Presentation"),
                            eq(JOptionPane.INFORMATION_MESSAGE)
                    )
            );
        }
    }
    
    @Test
    void testExecute_WithValidSlideNumber_SetsSlideNumber() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        
        // Use MockedStatic for JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Mock the input dialog to return "3"
            mockedStatic.when(() -> JOptionPane.showInputDialog(
                    any(),
                    anyString(),
                    anyString(),
                    anyInt()
            )).thenReturn("3");
            
            // When
            goToCommand.execute();
            
            // Then
            verify(mockPresentation, times(1)).setSlideNumber(2); // 3-1 because of 0-based index
        }
    }
    
    @Test
    void testExecute_WithInvalidSlideNumber_ShowsError() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        
        // Use MockedStatic for JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Set up the sequence of interactions
            mockedStatic.when(() -> JOptionPane.showInputDialog(
                    any(),
                    anyString(),
                    anyString(),
                    anyInt()
            )).thenReturn("6", null); // First return "6", then return null to exit the loop
            
            // When
            goToCommand.execute();
            
            // Then
            verify(mockPresentation, never()).setSlideNumber(anyInt());
            
            // Verify error message was shown
            mockedStatic.verify(() ->
                    JOptionPane.showMessageDialog(
                            any(),
                            eq("Please enter a number between 1 and 5"),
                            eq("Invalid Number"),
                            eq(JOptionPane.ERROR_MESSAGE)
                    )
            );
        }
    }
    
    @Test
    void testExecute_WithNonNumericInput_ShowsError() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        
        // Use MockedStatic for JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Set up the sequence of interactions
            mockedStatic.when(() -> JOptionPane.showInputDialog(
                    any(),
                    anyString(),
                    anyString(),
                    anyInt()
            )).thenReturn("abc", null); // First return "abc", then return null to exit the loop
            
            // When
            goToCommand.execute();
            
            // Then
            verify(mockPresentation, never()).setSlideNumber(anyInt());
            
            // Verify error message was shown
            mockedStatic.verify(() ->
                    JOptionPane.showMessageDialog(
                            any(),
                            eq("Invalid input. Please enter a numeric value."),
                            eq("Input Error"),
                            eq(JOptionPane.ERROR_MESSAGE)
                    )
            );
        }
    }
    
    @Test
    void testExecute_UserCancelsDialog() {
        // Given
        when(mockPresentation.getSize()).thenReturn(5);
        
        // Use MockedStatic for JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Mock the input dialog to return null (indicating user cancelled)
            mockedStatic.when(() -> JOptionPane.showInputDialog(
                    any(),
                    anyString(),
                    anyString(),
                    anyInt()
            )).thenReturn(null);
            
            // When
            goToCommand.execute();
            
            // Then
            verify(mockPresentation, never()).setSlideNumber(anyInt());
        }
    }
}