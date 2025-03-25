import java.awt.*;

public abstract class Command
{
    protected Presentation presentation;
    protected Frame parent;

    public Command()
    {
        this.parent = parent;
        this.presentation = presentation;

    }

    public abstract void execute();

}
