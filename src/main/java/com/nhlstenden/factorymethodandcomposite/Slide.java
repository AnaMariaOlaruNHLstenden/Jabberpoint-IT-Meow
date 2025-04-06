package com.nhlstenden.factorymethodandcomposite;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Vector;
import com.nhlstenden.ui.view.Drawable;
import com.nhlstenden.style.StyleManager;

/** <p>A slide. This class has a drawing functionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide implements Drawable, SlideComponent{
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	protected String title; // title is saved separately
	protected Vector<SlideComponent> items;

	public Slide() {
		items = new Vector<SlideComponent>();
	}

	// Add a slide item
	public void append(SlideComponent anItem) {
		items.addElement(anItem);
	}

	// give the title of the slide
	public String getTitle() {
		return title;
	}

	// change the title of the slide
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	// give the  SlideItem
	public SlideComponent getSlideItem(int number) {
		return (SlideItem)items.elementAt(number);
	}

	// give all SlideItems in a Vector
	public Vector<SlideComponent> getSlideItems() {
		return items;
	}

	// give the size of the Slide
	public int getSize() {
		return items.size();
	}
	
	@Override
	public void draw(Graphics g, int x, int y, float scale, ImageObserver observer, StyleManager styleManager) {
		int currentY = y;
		

		// Use Factory to create title item
		SlideItemFactory factory = new TextItemCreator();
		SlideItem titleItem = factory.createSlideItem(0, title);

		// Draw title
		titleItem.draw(g, x, currentY, scale, observer, styleManager);
		currentY += titleItem.getBoundingBox(g, observer, scale, styleManager).height;
		
		// Draw all items
		for (SlideComponent item : items) {
			SlideItem slideItem = (SlideItem) item;
			slideItem.draw(g, x, currentY, scale, observer, styleManager);
			currentY += slideItem.getBoundingBox(g, observer, scale, styleManager).height;
		}
	}
	
}
