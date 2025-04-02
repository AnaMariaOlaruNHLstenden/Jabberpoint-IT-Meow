// File: PresentationTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class PresentationTest {

    @Test
    public void testPresentationCreationAndTitle() {
        Presentation presentation = new Presentation("Demo Presentation");
        assertNotNull(presentation, "Presentation should be instantiated");
        assertEquals("Demo Presentation", presentation.getTitle(), "Title should be set correctly");
    }

    @Test
    public void testAddSlide() {
        Presentation presentation = new Presentation();
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();

        presentation.append(slide1);
        presentation.append(slide2);

        int slides = presentation.getSize();
        assertEquals(2, slides, "Presentation should contain two slides");
    }

    @Test
    public void testSlideNavigation() {
        Presentation presentation = new Presentation();
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        Slide slide3 = new Slide();

        presentation.append(slide1);
        presentation.append(slide2);
        presentation.append(slide3);

        // Assume methods to navigate slides (e.g., nextSlide, previousSlide)
        presentation.setSlideNumber(1);
        assertEquals("Intro", presentation.getCurrentSlide().getTitle(), "Current slide should be 'Intro'");

        presentation.nextSlide();
        assertEquals("Content", presentation.getCurrentSlide().getTitle(), "Current slide should be 'Content'");

        presentation.nextSlide();
        assertEquals("Conclusion", presentation.getCurrentSlide().getTitle(), "Current slide should be 'Conclusion'");
    }
}
