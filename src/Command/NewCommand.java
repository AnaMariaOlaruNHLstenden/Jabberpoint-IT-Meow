package Command;

import FactoryMethodAndComposite.Presentation;
import java.awt.*;

public class NewCommand extends Command
{

    public NewCommand(Presentation presentation, Frame parent)
    {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute()
    {
        presentation.clear();
        parent.repaint();
    }
}
