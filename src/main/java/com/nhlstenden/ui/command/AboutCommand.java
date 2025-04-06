package com.nhlstenden.ui.command;

import com.nhlstenden.ui.view.AboutBox;

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
