import Command.AboutCommand;

import java.awt.Frame;

public class TestAboutCommand extends AboutCommand{
    public TestAboutCommand(Frame parent) {
        super(parent);
    }

    @Override
    public void execute() {
        TestAboutBox.show(parent);
    }
} 