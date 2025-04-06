package com.nhlstenden.accessor;

import com.nhlstenden.app.DemoPresentation;
import com.nhlstenden.factorymethodandcomposite.Presentation;
import com.nhlstenden.factorymethodandcomposite.Slide;
import com.nhlstenden.factorymethodandcomposite.SlideItem;
import com.nhlstenden.factorymethodandcomposite.SlideItemFactory;
import com.nhlstenden.factorymethodandcomposite.TextItemCreator;
import com.nhlstenden.factorymethodandcomposite.BitmapItemCreator;
import com.nhlstenden.factorymethodandcomposite.TextItem;
import com.nhlstenden.factorymethodandcomposite.BitmapItem;
import com.nhlstenden.style.Style;
import com.nhlstenden.style.StyleManager;
import com.nhlstenden.ui.view.SlideViewer;
import com.nhlstenden.ui.view.SlideViewerComponent;
import com.nhlstenden.ui.view.SlideViewerFrame;
import com.nhlstenden.ui.command.Command;
import com.nhlstenden.ui.command.ExitCommand;
import com.nhlstenden.ui.command.OpenCommand;
import com.nhlstenden.ui.command.SaveCommand;
import com.nhlstenden.ui.command.NextSlideCommand;
import com.nhlstenden.ui.command.PrevSlideCommand;
import com.nhlstenden.ui.command.GoToCommand;
import com.nhlstenden.ui.command.MenuController;
import com.nhlstenden.ui.command.MenuCommandKey;
import com.nhlstenden.ui.command.KeyController;
import com.nhlstenden.ui.command.NewCommand;
import com.nhlstenden.ui.command.AboutCommand;




import java.io.IOException;

/**
 * <p>Een Accessor maakt het mogelijk om gegevens voor een presentatie
 * te lezen of te schrijven.</p>
 * <p>Niet-abstracte subklassen moeten de load en de save methode implementeren.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public abstract class Accessor {
	public static final String DEMO_NAME = "Demonstration presentation";
	public static final String DEFAULT_EXTENSION = ".xml";
	
	public Accessor() {
	}
	
	public static Accessor getDemoAccessor() {
		return new DemoPresentation();
	}

	abstract public void loadFile(Presentation p, String fn) throws IOException;

	abstract public void saveFile(Presentation p, String fn) throws IOException;

}
