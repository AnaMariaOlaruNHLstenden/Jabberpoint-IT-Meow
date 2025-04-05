package FactoryMethodAndComposite;

import Architecture.Drawable;
import Style.StyleManager;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/** <p>The abstract class for an item on a slide<p>
 * <p>All SlideItems have drawingfunctionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public abstract class SlideItem implements Drawable, SlideComponent{
	private int level;

	public SlideItem(int lev) {
		level = lev;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override
	public abstract void draw(Graphics g, int x, int y, float scale, ImageObserver observer, StyleManager styleManager);
	
	public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, StyleManager styleManager);
}
