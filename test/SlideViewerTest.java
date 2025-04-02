// File: SlideViewerTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlideViewerTest {

    @Test
    public void testRenderPresentation() {
        Presentation presentation = new Presentation();
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();
        presentation.append(slide1);
        presentation.append(slide2);

        // Assume SlideViewer takes a Presentation and renders the first slide
        SlideViewerFrame viewer = new SlideViewerFrame();
        String renderedContent = viewer.renderCurrentSlide();

        // For testing, assume the rendering returns the slide title
        assertTrue(renderedContent.contains("First Slide"), "Rendered content should include the title of the first slide");
    }

    @Test
    public void testSlideSwitchingInViewer() {
        Presentation presentation = new Presentation();
        presentation.addSlide(new Slide());
        presentation.addSlide(new Slide());
        presentation.addSlide(new Slide());

        SlideViewer viewer = new SlideViewer(presentation);
        assertEquals("Slide 1", viewer.getCurrentSlide().getTitle(), "Initially, first slide should be active");

        viewer.next();  // Move to next slide
        assertEquals("Slide 2", viewer.getCurrentSlide().getTitle(), "After next(), second slide should be active");

        viewer.previous();  // Move back to first slide
        assertEquals("Slide 1", viewer.getCurrentSlide().getTitle(), "After previous(), first slide should be active");
    }
}
