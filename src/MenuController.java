import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.util.HashMap;
import java.util.Map;

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
	
	private Frame parent;
	private Presentation presentation; // Commands given to presentation
	private Map<String,Command> commands;

	private static final long SERIAL_VERSION_UID = 227L;

	//Handle errors and alternatives
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
			setCommand(MenuCommandKey.NEXT.getLabel(), new NextSlideCommand(presentation));
			setCommand(MenuCommandKey.PREV.getLabel(), new PrevSlideCommand(presentation));
			setCommand(MenuCommandKey.EXIT.getLabel(), new ExitCommand(presentation, parent));
			setCommand(MenuCommandKey.OPEN.getLabel(), new OpenCommand(presentation, parent));
			setCommand(MenuCommandKey.SAVE.getLabel(), new SaveCommand(presentation, parent));
			setCommand(MenuCommandKey.NEW.getLabel(), new NewCommand(presentation, parent));
			setCommand(MenuCommandKey.GOTO.getLabel(), new GoToCommand(presentation));
			setCommand(MenuCommandKey.ABOUT.getLabel(), new AboutCommand(parent));

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
		Menu fileMenu = new Menu(MenuCommandKey.FILE.getLabel());

		MenuItem newItem = mkMenuItem(MenuCommandKey.NEW.getLabel());
		newItem.addActionListener(e -> executeCommand(MenuCommandKey.NEW.getLabel()));
		fileMenu.add(newItem);

		MenuItem openItem = mkMenuItem(MenuCommandKey.OPEN.getLabel());
		openItem.addActionListener(e -> executeCommand(MenuCommandKey.OPEN.getLabel()));
		fileMenu.add(openItem);

		MenuItem saveItem = mkMenuItem(MenuCommandKey.SAVE.getLabel());
		saveItem.addActionListener(e -> executeCommand(MenuCommandKey.SAVE.getLabel()));
		fileMenu.add(saveItem);

		fileMenu.addSeparator();

		MenuItem exitItem = mkMenuItem(MenuCommandKey.EXIT.getLabel());
		exitItem.addActionListener(e -> executeCommand(MenuCommandKey.EXIT.getLabel()));
		fileMenu.add(exitItem);

		return fileMenu;
	}

	private Menu createViewMenu() {
		Menu viewMenu = new Menu(MenuCommandKey.VIEW.getLabel());

		MenuItem nextItem = mkMenuItem(MenuCommandKey.NEXT.getLabel());
		nextItem.addActionListener(e -> executeCommand(MenuCommandKey.NEXT.getLabel()));
		viewMenu.add(nextItem);

		MenuItem prevItem = mkMenuItem(MenuCommandKey.PREV.getLabel());
		prevItem.addActionListener(e -> executeCommand(MenuCommandKey.PREV.getLabel()));
		viewMenu.add(prevItem);

		MenuItem gotoItem = mkMenuItem(MenuCommandKey.GOTO.getLabel());
		gotoItem.addActionListener(e -> executeCommand(MenuCommandKey.GOTO.getLabel()));
		viewMenu.add(gotoItem);

		return viewMenu;
	}

	private Menu createHelpMenu() {
		Menu helpMenu = new Menu(MenuCommandKey.HELP.getLabel());
		MenuItem aboutItem = mkMenuItem(MenuCommandKey.ABOUT.getLabel());
		aboutItem.addActionListener(e -> executeCommand(MenuCommandKey.ABOUT.getLabel()));
		helpMenu.add(aboutItem);
		return helpMenu;
	}

	// create a menu item
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
