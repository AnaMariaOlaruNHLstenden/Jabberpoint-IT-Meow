import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

// DummySlide: Minimal concrete subclass of Slide for testing non-UI methods.
class DummySlide extends Slide {
    // No additional implementation is needed for non-UI testing.
}

// DummySlideViewerComponent: Extends SlideViewerComponent to capture update calls.
class DummySlideViewerComponent extends SlideViewerComponent {

    private Presentation lastPresentation;
    private Slide lastSlide;

    public DummySlideViewerComponent() {
        // To satisfy the superclass constructor, we need to pass a Presentation and a JFrame.
        // We create a temporary Presentation using a placeholder DummyViewer.
        super(new Presentation(new DummySlideViewerComponentPlaceholder()), new JFrame("Dummy"));
    }

    // A minimal placeholder to avoid circular dependency.
    private static class DummySlideViewerComponentPlaceholder extends SlideViewerComponent {
        public DummySlideViewerComponentPlaceholder() {
            super(null, new JFrame());
        }

        @Override
        public void update(Presentation presentation, Slide data) {
            // No action needed for the placeholder.
        }
    }

    @Override
    public void update(Presentation presentation, Slide data) {
        this.lastPresentation = presentation;
        this.lastSlide = data;
    }

    public Presentation getLastPresentation() {
        return lastPresentation;
    }

    public Slide getLastSlide() {
        return lastSlide;
    }
}

public class PresentationTest {

    private Presentation presentation;
    private DummySlideViewerComponent dummyViewer;

    @BeforeEach
    public void setUp() {
        // Create a dummy viewer and pass it to the Presentation constructor.
        dummyViewer = new DummySlideViewerComponent();
        presentation = new Presentation(dummyViewer);
    }

    @Test
    public void testTitleMethods() {
        // Initially, title should be null.
        assertNull(presentation.getTitle());
        // Set a title and verify.
        presentation.setTitle("My Presentation");
        assertEquals("My Presentation", presentation.getTitle());
    }

    @Test
    public void testAppendAndGetSlide() {
        DummySlide slide1 = new DummySlide();
        DummySlide slide2 = new DummySlide();

        presentation.append(slide1);
        presentation.append(slide2);

        // Verify that the number of slides is 2.
        assertEquals(2, presentation.getSize());
        // Verify that the slides are retrieved correctly.
        assertSame(slide1, presentation.getSlide(0));
        assertSame(slide2, presentation.getSlide(1));
    }

    @Test
    public void testGetSlide_InvalidIndex() {
        DummySlide slide = new DummySlide();
        presentation.append(slide);
        // Verify that invalid indices return null.
        assertNull(presentation.getSlide(-1));
        assertNull(presentation.getSlide(1));
    }

    @Test
    public void testClear() {
        DummySlide slide = new DummySlide();
        presentation.append(slide);
        assertEquals(1, presentation.getSize());
        // clear() should reset the slide list and set slide number to -1.
        presentation.clear();
        assertEquals(0, presentation.getSize());
        assertEquals(-1, presentation.getSlideNumber());
    }

    @Test
    public void testNavigation() {
        DummySlide slide1 = new DummySlide();
        DummySlide slide2 = new DummySlide();
        DummySlide slide3 = new DummySlide();
        presentation.append(slide1);
        presentation.append(slide2);
        presentation.append(slide3);

        // Set starting slide to 0.
        presentation.setSlideNumber(0);
        assertSame(slide1, presentation.getCurrentSlide());

        // Test nextSlide().
        presentation.nextSlide();
        assertEquals(1, presentation.getSlideNumber());
        assertSame(slide2, presentation.getCurrentSlide());

        // nextSlide() should not go beyond the last slide.
        presentation.nextSlide();
        assertEquals(2, presentation.getSlideNumber());
        assertSame(slide3, presentation.getCurrentSlide());
        presentation.nextSlide();
        assertEquals(2, presentation.getSlideNumber());

        // Test prevSlide().
        presentation.prevSlide();
        assertEquals(1, presentation.getSlideNumber());
        assertSame(slide2, presentation.getCurrentSlide());
        presentation.setSlideNumber(0);
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    public void testSetSlideNumberWithViewer() {
        DummySlide slide1 = new DummySlide();
        DummySlide slide2 = new DummySlide();
        presentation.append(slide1);
        presentation.append(slide2);

        // When slide number is set, the dummy viewer's update() should be called.
        presentation.setSlideNumber(0);
        assertSame(slide1, presentation.getCurrentSlide());
        assertSame(presentation, dummyViewer.getLastPresentation());
        assertSame(slide1, dummyViewer.getLastSlide());

        presentation.setSlideNumber(1);
        assertSame(slide2, presentation.getCurrentSlide());
        assertSame(presentation, dummyViewer.getLastPresentation());
        assertSame(slide2, dummyViewer.getLastSlide());
    }

}
