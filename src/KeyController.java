import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class KeyController extends KeyAdapter implements KeyListener
{
	private Presentation presentation; // Commands are given to the presentation
	private Frame parent;
	private Map<Integer, Command> keyCommands; // Maps key codes to commands

	public KeyController(Presentation presentation) {
		this.presentation = presentation;
        this.parent = parent;
        keyCommands = new HashMap<>();

		// Create command instances
		Command nextCommand = new NextSlideCommand(presentation);
		Command prevCommand = new PrevSlideCommand(presentation);
		Command exitCommand = new ExitCommand(presentation, parent);
		Command openCommand = new OpenCommand(presentation, parent);
		Command saveCommand = new SaveCommand(presentation, parent);
		Command newCommand = new NewCommand(presentation, parent);
		Command gotoCommand = new GoToCommand(presentation);
		Command aboutCommand = new AboutCommand(parent);

		// Set up key commands
		setKeyCommand(KeyEvent.VK_PAGE_DOWN, nextCommand);
		setKeyCommand(KeyEvent.VK_DOWN, nextCommand);
		setKeyCommand(KeyEvent.VK_ENTER, nextCommand);
		setKeyCommand(KeyEvent.VK_PLUS, nextCommand);

		setKeyCommand(KeyEvent.VK_PAGE_UP, prevCommand);
		setKeyCommand(KeyEvent.VK_UP, prevCommand);
		setKeyCommand(KeyEvent.VK_MINUS, prevCommand);

		setKeyCommand(KeyEvent.VK_G, gotoCommand);
		setKeyCommand(KeyEvent.VK_O, openCommand);
		setKeyCommand(KeyEvent.VK_N, newCommand);
		setKeyCommand(KeyEvent.VK_S, saveCommand);
		setKeyCommand(KeyEvent.VK_H, aboutCommand);
		setKeyCommand(KeyEvent.VK_Q, exitCommand);
	}

	// This method allows users to set their own key commands.
	public void setKeyCommand(int keyCode, Command command) {
		if (keyCommands.containsKey(keyCode)) {
			throw new IllegalArgumentException("Key code " + keyCode + " is already assigned to another command.");
		}
		keyCommands.put(keyCode, command);
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		Command command = keyCommands.get(keyEvent.getKeyCode());
		if (command != null) {
			command.execute();
		}
	}

}
