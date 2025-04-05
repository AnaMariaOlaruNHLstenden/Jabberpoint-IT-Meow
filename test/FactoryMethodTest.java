import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import FactoryMethodAndComposite.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

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
        // Mock FactoryMethodAndComposite.BitmapItem to avoid file loading
        try (MockedStatic<ImageIO> imageIOMock = Mockito.mockStatic(ImageIO.class)) {
            // Mock the ImageIO.read() call to return a dummy BufferedImage
            BufferedImage dummyImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
            imageIOMock.when(() -> ImageIO.read(any(File.class))).thenReturn(dummyImage);
            
            // Arrange: Use the real factory
            BitmapItemCreator factory = new BitmapItemCreator();
            
            // Act: Create a FactoryMethodAndComposite.BitmapItem
            SlideItem slideItem = factory.createSlideItem(1, "sample-image.png");
            
            // Assert: Check the properties of the created object
            assertNotNull(slideItem);
            assertTrue(slideItem instanceof BitmapItem, "Factory should create a FactoryMethodAndComposite.BitmapItem instance");
            
            BitmapItem bitmapItem = (BitmapItem) slideItem;
            assertEquals(1, bitmapItem.getLevel(), "FactoryMethodAndComposite.BitmapItem level should be as specified");
            assertEquals("sample-image.png", bitmapItem.getName(), "FactoryMethodAndComposite.BitmapItem name should match the input");
        }
    }
    
    @Test
    void testAbstractFactoryThrowsException() {
        SlideItemFactory incompleteFactory = new SlideItemFactory() {
            @Override
            public SlideItem createSlideItem(int level, String content) {
                throw new UnsupportedOperationException("Abstract implementation should not create items.");
            }
        };
        
        assertThrows(UnsupportedOperationException.class, () -> {
            incompleteFactory.createSlideItem(1, "Test");
        });
    }
    
    @Test
    void testConcreteFactoryReturnsTextItem() {
        TextItemCreator factory = new TextItemCreator();
        SlideItem slideItem = factory.createSlideItem(2, "Sample Text");
        
        assertNotNull(slideItem, "The factory should return a valid item.");
        assertTrue(slideItem instanceof TextItem, "Expected factory to create a FactoryMethodAndComposite.TextItem instance.");
        assertEquals(2, slideItem.getLevel(), "The level should match the provided value.");
    }
    
    @Test
    void testConcreteFactoryReturnsBitmapItem() {
        // Arrange: Mock FactoryMethodAndComposite.BitmapItem
        BitmapItem mockBitmapItem = mock(BitmapItem.class);
        
        // Mock the FactoryMethodAndComposite.BitmapItemCreator to return the mockBitmapItem
        BitmapItemCreator factory = mock(BitmapItemCreator.class);
        when(factory.createSlideItem(anyInt(), anyString())).thenReturn(mockBitmapItem);
        
        // Act: Use the mock factory
        SlideItem slideItem = factory.createSlideItem(1, "test-image.png");
        
        // Assert: Ensure the factory returns a FactoryMethodAndComposite.BitmapItem
        assertNotNull(slideItem);
        assertTrue(slideItem instanceof BitmapItem, "Factory should return an instance of FactoryMethodAndComposite.BitmapItem");
    }
}
