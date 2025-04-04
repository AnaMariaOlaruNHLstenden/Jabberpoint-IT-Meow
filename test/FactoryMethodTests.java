import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FactoryMethodTests{
    private SlideItemFactory textFactory;
    private SlideItemFactory bitmapFactory;
    
    @BeforeEach
    void setUp() {
        textFactory = new TextItemCreator();
        bitmapFactory = new BitmapItemCreator();
    }
    
    @Test
    void testTextItemFactoryCreatesTextItem() {
        SlideItem item = textFactory.createSlideItem(1, "Test Text");
        assertNotNull(item);
        assertTrue(item instanceof TextItem);
        assertEquals("Test Text", ((TextItem) item).getText());
    }
    
    @Test
    void testBitmapItemFactoryCreatesBitmapItem() {
        SlideItem item = bitmapFactory.createSlideItem(2, "testImage.png");
        assertNotNull(item);
        assertTrue(item instanceof BitmapItem);
        assertEquals("testImage.png", ((BitmapItem) item).getName());
    }
}
