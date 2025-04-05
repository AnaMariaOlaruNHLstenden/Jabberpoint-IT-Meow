import Architecture.SlideViewerComponent;
import FactoryMethodAndComposite.Presentation;
import FactoryMethodAndComposite.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PresentationTest {
    private Presentation presentation;
    private SlideViewerComponent mockSlideViewer;
    private Slide mockSlide;
    
    @BeforeEach
    public void setUp() {
        mockSlideViewer = mock(SlideViewerComponent.class);
        mockSlide = mock(Slide.class);
        presentation = new Presentation(mockSlideViewer);
    }
    
    @Test
    public void testPresentationInitialization() {
        assertEquals(0, presentation.getSize());
        assertEquals(-1, presentation.getSlideNumber());
        assertNull(presentation.getTitle());
    }
    
    @Test
    public void testSetTitle() {
        String expectedTitle = "My Test FactoryMethodAndComposite.Presentation";
        presentation.setTitle(expectedTitle);
        assertEquals(expectedTitle, presentation.getTitle());
    }
    
    @Test
    public void testAddingSlides() {
        presentation.append(mockSlide);
        assertEquals(1, presentation.getSize());
        assertEquals(mockSlide, presentation.getSlide(0));
    }
    
    @Test
    public void testNavigationBetweenSlides() {
        Slide mockSlide2 = mock(Slide.class);
        presentation.append(mockSlide);
        presentation.append(mockSlide2);
        
        presentation.nextSlide();
        assertEquals(0, presentation.getSlideNumber());
        
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
    }
    
    @Test
    public void testSlideNavigationBoundary() {
        presentation.append(mockSlide);
        
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
        
        presentation.nextSlide();
        assertEquals(0, presentation.getSlideNumber());
    }
    
    @Test
    public void testClearPresentation() {
        presentation.append(mockSlide);
        presentation.clear();
        
        assertEquals(0, presentation.getSize());
        assertEquals(-1, presentation.getSlideNumber());
    }
    
    @Test
    public void testGetCurrentSlide() {
        Slide mockSlide2 = mock(Slide.class);
        presentation.append(mockSlide);
        presentation.append(mockSlide2);
        
        presentation.setSlideNumber(1);
        assertEquals(mockSlide2, presentation.getCurrentSlide());
    }
    
    @Test
    public void testEmptyPresentationBehavior() {
        assertNull(presentation.getSlide(0)); // Expect null instead of exception
        presentation.nextSlide();
        assertEquals(0, presentation.getSlideNumber());
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
    }
    
    @Test
    public void testSetSlideNumberUpdatesView() {
        presentation.append(mockSlide);
        presentation.setSlideNumber(0);
        verify(mockSlideViewer, times(1)).update(presentation, mockSlide);
    }
}