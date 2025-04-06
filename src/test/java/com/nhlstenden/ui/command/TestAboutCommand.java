package com.nhlstenden.ui.command;

import java.awt.Frame;
import com.nhlstenden.ui.view.AboutBox;
import com.nhlstenden.ui.view.TestAboutBox;
import com.nhlstenden.ui.command.AboutCommand;

public class TestAboutCommand extends AboutCommand {
    public TestAboutCommand(Frame parent) {
        super(parent);
    }

    @Override
    public void execute() {
        TestAboutBox.show(parent);
    }
} 