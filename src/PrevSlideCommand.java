public class PrevSlideCommand extends Command
{

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
