package com.nhlstenden.ui.command;

import java.awt.*;
import com.nhlstenden.factorymethodandcomposite.Presentation;
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
