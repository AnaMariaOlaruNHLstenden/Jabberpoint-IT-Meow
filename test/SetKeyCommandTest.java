import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.awt.Frame;

// Mock classes for testing
class TestPresentation { }

abstract class TestCommand {
    protected TestPresentation presentation;
    protected Frame parent;

    public TestCommand() {
        // In this example, no initialization is provided as it's not used in tests.
    }

    public abstract void execute();
}

// DummyCommand subclass that provides a minimal implementation of execute()
class TestDummyCommand extends TestCommand {
    public TestDummyCommand() {
        // Optionally initialize presentation and parent if necessary
    }

    @Override
    public void execute() {
        // Minimal implementation for testing purposes
    }
}

// Class containing the method to test
class SetKeyCommandSetup {
    private Map<Integer, TestCommand> keyCommands = new HashMap<>();

    public void setKeyCommand(int keyCode, TestCommand command) {
        if (keyCommands.containsKey(keyCode)) {
            throw new IllegalArgumentException("Key code " + keyCode + " is already assigned to another command.");
        }
        keyCommands.put(keyCode, command);
    }

    public Map<Integer, TestCommand> getKeyCommands() {
        return keyCommands;
    }
}

public class SetKeyCommandTest {
    @Test
    public void testSetKeyCommand_Success() {
        SetKeyCommandSetup setup = new SetKeyCommandSetup();
        TestCommand saveCommand = new TestDummyCommand();
        // Add key command with VK_S key code
        setup.setKeyCommand(KeyEvent.VK_S, saveCommand);
        // Verify that the command was added successfully to the map
        assertEquals(saveCommand, setup.getKeyCommands().get(KeyEvent.VK_S));
    }

    @Test
    public void testSetKeyCommand_DuplicateKey() {
        SetKeyCommandSetup setup = new SetKeyCommandSetup();
        TestCommand saveCommand1 = new TestDummyCommand();
        TestCommand saveCommand2 = new TestDummyCommand();
        // First assignment should succeed
        setup.setKeyCommand(KeyEvent.VK_S, saveCommand1);
        // Second assignment with the same key should throw an exception
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            setup.setKeyCommand(KeyEvent.VK_S, saveCommand2);
        });
        String expectedMessage = "Key code " + KeyEvent.VK_S + " is already assigned to another command.";
        assertEquals(expectedMessage, thrown.getMessage());
    }
}
