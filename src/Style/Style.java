package Style;

import java.awt.Color;
import java.awt.Font;

/** <p>Style.Style is for Indent, Color, Font and Leading.</p>
 * <p>Direct relation between style-number and item-level:
 * in FactoryMethodAndComposite.Slide style if fetched for an item
 * with style-number as item-level.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style {

	private static final String FONTNAME = "Helvetica";
	private final int INDENT;
	private final Color COLOR;
	private final Font FONT;
	private final int FONT_SIZE;
	private final int LEADING;

	public Style(int indent, Color color, int fontSize, int leading) {
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
