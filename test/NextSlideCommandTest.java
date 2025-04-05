import Command.NextSlideCommand;
import FactoryMethodAndComposite.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class NextSlideCommandTest {
    private Presentation mockPresentation;
    private NextSlideCommand nextSlideCommand;

    @BeforeEach
    void setUp() {
        mockPresentation = mock(Presentation.class);
        nextSlideCommand = new NextSlideCommand(mockPresentation);
    }

    @Test
    void testExecute_CallsNextSlide() {
        // When
        nextSlideCommand.execute();

        // Then
        verify(mockPresentation, times(1)).nextSlide();
    }
} 