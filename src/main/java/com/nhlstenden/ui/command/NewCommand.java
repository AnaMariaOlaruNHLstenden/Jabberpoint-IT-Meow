package com.nhlstenden.ui.command;

import java.awt.*;
import com.nhlstenden.factorymethodandcomposite.Presentation;

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
