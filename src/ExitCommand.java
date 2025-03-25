import java.awt.*;

public class ExitCommand extends Command
{

    public ExitCommand(Presentation presentation, Frame parent)
    {
        this.presentation = presentation;
        this.parent = parent;
    }

    public void execute() {
        presentation.exit(0);
    }
}
