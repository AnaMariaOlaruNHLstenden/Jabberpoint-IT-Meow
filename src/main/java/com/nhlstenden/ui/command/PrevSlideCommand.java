package com.nhlstenden.ui.command;

import com.nhlstenden.factorymethodandcomposite.Presentation;

public class PrevSlideCommand extends Command{

    public PrevSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute()
    {
        presentation.prevSlide();
    }
}
