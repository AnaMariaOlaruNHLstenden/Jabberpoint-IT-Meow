package Command;

import FactoryMethodAndComposite.Presentation;

import java.awt.*;

public abstract class Command
{
    protected Presentation presentation;
    public Frame parent;

    public Command()
    {
        this.parent = parent;
        this.presentation = presentation;

    }

    public abstract void execute();

}
