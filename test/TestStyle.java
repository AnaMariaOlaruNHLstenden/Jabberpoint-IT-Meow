import java.awt.Color;
import java.awt.Font;

public class TestStyle {
    private static final String FONTNAME = "Helvetica";
    private final int INDENT;
    private final Color COLOR;
    private final Font FONT;
    private final int FONT_SIZE;
    private final int LEADING;

    public TestStyle(int indent, Color color, int fontSize, int leading) {
        this.INDENT = indent;
        this.COLOR = color;
        this.FONT = new Font(FONTNAME, Font.BOLD, fontSize);
        this.FONT_SIZE = fontSize;
        this.LEADING = leading;
    }

    public int getIndent() {
        return INDENT;
    }

    public Color getColor() {
        return COLOR;
    }

    public Font getFont(float scale) {
        return FONT.deriveFont(FONT_SIZE * scale);
    }

    public int getLeading() {
        return LEADING;
    }

    @Override
    public String toString() {
        return "[" + INDENT + "," + COLOR + "; " + FONT_SIZE + " on " + LEADING + "]";
    }
} 