// File: SlideTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SlideTest {

    @Test
    public void testSlideConstructorAndTitle() {
        Slide slide = new Slide();
        assertNotNull(slide, "Slide object should be instantiated");
        assertEquals("Introduction", slide.getTitle(), "Slide title should be set correctly");
    }

    @Test
    public void testSetTitle() {
        Slide slide = new Slide();
        slide.setTitle("New Title");
        assertEquals("New Title", slide.getTitle(), "Slide title should update correctly");
    }

    @Test
    public void testAddSlideItem() {
        Slide slide = new Slide();
        SlideItem item = new SlideItem("Sample Text", 1); // Assume level 1 for indentation
        slide.items(item);

        List<SlideItem> items = sli;
        assertEquals(1, items.size(), "Slide should contain one item");
        assertEquals("Sample Text", items.get(0).getContent(), "Item content should match");
    }
}
