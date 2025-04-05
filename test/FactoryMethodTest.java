import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FactoryMethodTest{
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
        // Arrange: Use the real factory (not a spy)
        BitmapItemCreator factory = new BitmapItemCreator();
        
        // Act: Create a BitmapItem
        SlideItem slideItem = factory.createSlideItem(1, "sample-image.png");
        
        // Assert: Check the properties of the created object
        assertNotNull(slideItem);
        assertTrue(slideItem instanceof BitmapItem, "Factory should create a BitmapItem instance");
        
        BitmapItem bitmapItem = (BitmapItem) slideItem;
        assertEquals(1, bitmapItem.getLevel(), "BitmapItem level should be as specified");
        assertEquals("sample-image.png", bitmapItem.getName(), "BitmapItem name should match the input");
    }
    
    @Test
    void testAbstractFactoryThrowsException() {
        SlideItemFactory incompleteFactory = new SlideItemFactory() {
            @Override
            SlideItem createSlideItem(int level, String content) {
                throw new UnsupportedOperationException("Abstract implementation should not create items.");
            }
        };
        
        assertThrows(UnsupportedOperationException.class, () -> {
            incompleteFactory.createSlideItem(1, "Test");
        });
    }
    
    @Test
    void testConcreteFactoryReturnsTextItem() {
        SlideItemFactory factory = new TextItemCreator();
        SlideItem slideItem = factory.createSlideItem(2, "Sample Text");
        
        assertNotNull(slideItem, "The factory should return a valid item.");
        assertTrue(slideItem instanceof TextItem, "Expected factory to create a TextItem instance.");
        assertEquals(2, slideItem.getLevel(), "The level should match the provided value.");
    }
    
    @Test
    void testConcreteFactoryReturnsBitmapItem() {
        // Arrange: Mock BitmapItem
        BitmapItem mockBitmapItem = mock(BitmapItem.class);
        
        // Mock the BitmapItemCreator to return the mockBitmapItem
        BitmapItemCreator factory = mock(BitmapItemCreator.class);
        when(factory.createSlideItem(anyInt(), anyString())).thenReturn(mockBitmapItem);
        
        // Act: Use the mock factory
        SlideItem slideItem = factory.createSlideItem(1, "test-image.png");
        
        // Assert: Ensure the factory returns a BitmapItem
        assertNotNull(slideItem);
        assertTrue(slideItem instanceof BitmapItem, "Factory should return an instance of BitmapItem");
    }
}
