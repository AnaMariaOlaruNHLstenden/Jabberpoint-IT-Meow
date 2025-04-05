package Command;

import App.AboutBox;

import java.awt.*;

public class AboutCommand extends Command
{

    public AboutCommand(Frame parent)
    {
        this.parent = parent;
    }

    @Override
    public void execute()
    {
        AboutBox.show(parent);
    }

}
