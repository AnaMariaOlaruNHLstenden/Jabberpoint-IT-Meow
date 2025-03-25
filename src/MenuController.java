import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{
	
	private Frame parent; // the frame, only used as parent for the Dialogs
	private Presentation presentation; // Commands are given to the presentation
	private Map<String,Command> commands; // Storing commands in a HashMap

	private static final long serialVersionUID = 227L;

	//No changes due to easy Maintainability of the code
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";
	
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Presentation presentation) {
			this.parent = frame;
			this.presentation = presentation;
			this.commands = new HashMap<>();

			// Create and register command objects
			setCommand(NEXT, new NextSlideCommand(presentation));
			setCommand(PREV, new PrevSlideCommand(presentation));
			setCommand(EXIT, new ExitCommand(presentation, parent));
			setCommand(OPEN, new OpenCommand(presentation, parent));
			setCommand(SAVE, new SaveCommand(presentation, parent));
			setCommand(NEW, new NewCommand(presentation, parent));
			setCommand(GOTO, new GoToCommand(presentation));
			setCommand(ABOUT, new AboutCommand(parent));

			// Build menus
			add(createFileMenu());
			add(createViewMenu());
			setHelpMenu(createHelpMenu());// needed for portability (Motif, etc.).
	}

	public void setCommand(String name, Command command)
	{
		commands.put(name, command);
	}

	public void executeCommand(String name){
		Command command = commands.get(name);
		if(command != null){
			command.execute();
		}
	}

	private Menu createFileMenu() {
		Menu fileMenu = new Menu(FILE);
		MenuItem openItem = mkMenuItem(OPEN);
		openItem.addActionListener(e -> executeCommand(OPEN));
		fileMenu.add(openItem);

		MenuItem saveItem = mkMenuItem(SAVE);
		saveItem.addActionListener(e -> executeCommand(SAVE));
		fileMenu.add(saveItem);

		fileMenu.addSeparator();

		MenuItem exitItem = mkMenuItem(EXIT);
		exitItem.addActionListener(e -> executeCommand(EXIT));
		fileMenu.add(exitItem);

		return fileMenu;
	}

	private Menu createViewMenu() {
		Menu viewMenu = new Menu(VIEW);

		MenuItem nextItem = mkMenuItem(NEXT);
		nextItem.addActionListener(e -> executeCommand(NEXT));
		viewMenu.add(nextItem);

		MenuItem prevItem = mkMenuItem(PREV);
		prevItem.addActionListener(e -> executeCommand(PREV));
		viewMenu.add(prevItem);

		MenuItem gotoItem = mkMenuItem(GOTO);
		gotoItem.addActionListener(e -> executeCommand(GOTO));
		viewMenu.add(gotoItem);

		return viewMenu;
	}

	private Menu createHelpMenu() {
		Menu helpMenu = new Menu(HELP);
		MenuItem aboutItem = mkMenuItem(ABOUT);
		aboutItem.addActionListener(e -> executeCommand(ABOUT));
		helpMenu.add(aboutItem);
		return helpMenu;
	}

// create a menu item
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
