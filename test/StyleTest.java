import Style.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.Font;

public class StyleTest {
    private Style style;
    private static final int TEST_INDENT = 20;
    private static final Color TEST_COLOR = Color.BLUE;
    private static final int TEST_FONT_SIZE = 40;
    private static final int TEST_LEADING = 10;

    @BeforeEach
    void setUp() {
        style = new Style(TEST_INDENT, TEST_COLOR, TEST_FONT_SIZE, TEST_LEADING);
    }

    @Test
    void testGetIndent() {
        assertEquals(TEST_INDENT, style.getIndent());
    }

    @Test
    void testGetColor() {
        assertEquals(TEST_COLOR, style.getColor());
    }

    @Test
    void testGetFont() {
        float scale = 1.5f;
        Font scaledFont = style.getFont(scale);
        
        // Font name might be platform-dependent, so we'll just verify it's not null
        assertNotNull(scaledFont.getFontName());
        assertEquals(Font.BOLD, scaledFont.getStyle());
        assertEquals(Math.round(TEST_FONT_SIZE * scale), scaledFont.getSize());
    }

    @Test
    void testGetLeading() {
        assertEquals(TEST_LEADING, style.getLeading());
    }

    @Test
    void testToString() {
        String expected = "[" + TEST_INDENT + "," + TEST_COLOR + "; " + TEST_FONT_SIZE + " on " + TEST_LEADING + "]";
        assertEquals(expected, style.toString());
    }
} 