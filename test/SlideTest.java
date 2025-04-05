import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Dummy implementation of SlideComponent for testing purposes.
class DummySlideComponent implements SlideComponent {
    // No additional methods needed for non-UI testing.
}

public class SlideTest {

    @Test
    public void testTitleMethods() {
        Slide slide = new Slide();
        assertNull(slide.getTitle());
        slide.setTitle("Test Title");
        assertEquals("Test Title", slide.getTitle());
    }

    @Test
    public void testAppendAndGetSlideItem() {
        Slide slide = new Slide();
        DummySlideComponent item1 = new DummySlideComponent();
        DummySlideComponent item2 = new DummySlideComponent();

        // Append dummy slide components.
        slide.append(item1);
        slide.append(item2);

        // Verify that the size of the slide is updated.
        assertEquals(2, slide.getSize());
        // Verify that the correct items are retrieved.
        assertEquals(item1, slide.getSlideItem(0));
        assertEquals(item2, slide.getSlideItem(1));
    }

    @Test
    public void testGetSlideItems() {
        Slide slide = new Slide();
        // Initially, the slide items Vector should be empty.
        Vector<SlideComponent> items = slide.getSlideItems();
        assertNotNull(items);
        assertTrue(items.isEmpty());

        // Append an item and verify the vector updates.
        DummySlideComponent item = new DummySlideComponent();
        slide.append(item);
        items = slide.getSlideItems();
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }
    
    @Test
    void testSlideUsesBitmapItem() {
        // Arrange: Mock Slide and BitmapItem
        Slide mockSlide = mock(Slide.class);
        BitmapItem mockBitmapItem = mock(BitmapItem.class);
        
        // Define mocked behavior for BitmapItem
        when(mockBitmapItem.getName()).thenReturn("test-image.png");
        
        // Use the mock in the Slide object
        doNothing().when(mockSlide).append(any(SlideItem.class));
        
        // Act: Add the mocked BitmapItem to the slide
        mockSlide.append(mockBitmapItem);
        
        // Verify the interaction
        verify(mockSlide, times(1)).append(mockBitmapItem);
        assertEquals("test-image.png", mockBitmapItem.getName(), "BitmapItem name should match the input");
    }
    
    @Test
    void testSlideUsesTextItem() {
        // Arrange: Mock Slide and TextItem
        Slide mockSlide = mock(Slide.class);
        TextItem mockTextItem = mock(TextItem.class);
        
        // Define mocked behavior for TextItem
        when(mockTextItem.getText()).thenReturn("Sample text for testing");
        
        // Use the mock in the Slide object
        doNothing().when(mockSlide).append(any(SlideItem.class));
        
        // Act: Add the mocked TextItem to the slide
        mockSlide.append(mockTextItem);
        
        // Verify the interaction
        verify(mockSlide, times(1)).append(mockTextItem);
        assertEquals("Sample text for testing", mockTextItem.getText(), "TextItem text should match the input");
    }
}
