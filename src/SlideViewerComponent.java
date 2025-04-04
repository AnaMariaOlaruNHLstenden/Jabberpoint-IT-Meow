import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;


/** <p>SlideViewerComponent is a graphical component that can show slides.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent implements SlideViewer {
		
	private Slide slide; // current slide
	private Font labelFont = null; // font for labels
	private Presentation presentation = null; // the presentation
	private JFrame frame = null;

	private static final long SERIAL_VERSION_UID = 227L;
	
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	public SlideViewerComponent(Presentation pres, JFrame frame, StyleManager styleManager) {
		setBackground(BGCOLOR);
		presentation = pres;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
		this.STYLE_MANAGER = styleManager;
	}

	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	@Override
	public void update(Presentation presentation, Slide data)
	{
		if (data == null) {
			repaint();
			return;
		}
		this.presentation = presentation;
		this.slide = data;
		repaint();
		frame.setTitle(presentation.getTitle());
	}

	// draw the slide
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Make sure component is properly refreshed
		
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		
		// Check if slide is available
		if (presentation.getSlideNumber() < 0 || slide == null) {
			return;
		}
		
		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(), XPOS, YPOS);
		
		// Define where slide content will be drawn
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		
		int y = YPOS + 40;
		float scale = 1.0f;

		for (SlideComponent item : slide.getSlideItems()) {
			if (!(item instanceof SlideItem)) {
				continue; // Skip if it's not a SlideItem
			}

			SlideItem slideItem = (SlideItem) item;
			Style style = STYLE_MANAGER.getStyle(slideItem.getLevel());

			slideItem.draw(g, 0, y, scale, this, STYLE_MANAGER);
			y += style.getLeading() + style.getFont(1.0f).getSize();
		}
	}

}
