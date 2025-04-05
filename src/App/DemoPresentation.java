package App;

import FactoryMethodAndComposite.*;

/** A built in demo-presentation
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation extends Accessor {

	public void loadFile(Presentation presentation, String unusedFilename) {
		presentation.setTitle("Demo FactoryMethodAndComposite.Presentation");
		Slide slide;
		slide = new Slide();
		TextItemCreator textFactory = new TextItemCreator();  // Factory for TextItems
		BitmapItemCreator bitmapFactory = new BitmapItemCreator();  // Factory for

		slide.setTitle("App.JabberPoint");
		slide.append(textFactory.createSlideItem(1, "The Java FactoryMethodAndComposite.Presentation Tool"));
		slide.append(textFactory.createSlideItem(2, "Copyright (c) 1996-2000: Ian Darwin"));
		slide.append(textFactory.createSlideItem(2, "Copyright (c) 2000-now:"));
		slide.append(textFactory.createSlideItem(2, "Gert Florijn and Sylvia Stuurman"));
		slide.append(textFactory.createSlideItem(4, "Starting App.JabberPoint without a filename"));
		slide.append(textFactory.createSlideItem(4, "shows this presentation"));
		slide.append(textFactory.createSlideItem(1, "Navigate:"));
		slide.append(textFactory.createSlideItem(3, "Next slide: PgDn or Enter"));
		slide.append(textFactory.createSlideItem(3, "Previous slide: PgUp or up-arrow"));
		slide.append(textFactory.createSlideItem(3, "Quit: q or Q"));
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("Demonstration of levels and styles");
		slide.append(textFactory.createSlideItem(1, "Level 1"));
		slide.append(textFactory.createSlideItem(2, "Level 2"));
		slide.append(textFactory.createSlideItem(1, "Again level 1"));
		slide.append(textFactory.createSlideItem(1, "Level 1 has style number 1"));
		slide.append(textFactory.createSlideItem(2, "Level 2 has style number 2"));
		slide.append(textFactory.createSlideItem(3, "This is how level 3 looks like"));
		slide.append(textFactory.createSlideItem(4, "And this is level 4"));
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("The third slide");
		slide.append(textFactory.createSlideItem(1, "To open a new presentation,"));
		slide.append(textFactory.createSlideItem(2, "use File->Open from the menu."));
		slide.append(textFactory.createSlideItem(1, " "));
		slide.append(textFactory.createSlideItem(1, "This is the end of the presentation."));
		slide.append(bitmapFactory.createSlideItem(1, "App.JabberPoint.gif")); // Use factory for FactoryMethodAndComposite.BitmapItem
		presentation.append(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Save As->Demo! called");
	}
}
