package Command;

import FactoryMethodAndComposite.Presentation;

public class NextSlideCommand extends Command
{

    public NextSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute()
    {
        presentation.nextSlide();
    }
}
