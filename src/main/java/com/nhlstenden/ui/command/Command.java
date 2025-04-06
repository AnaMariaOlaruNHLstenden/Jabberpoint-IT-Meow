package com.nhlstenden.ui.command;

import java.awt.*;
import com.nhlstenden.factorymethodandcomposite.Presentation;

public abstract class Command
{
    protected Presentation presentation;
    protected Frame parent;

    public Command()
    {
    }

    public abstract void execute();

}
