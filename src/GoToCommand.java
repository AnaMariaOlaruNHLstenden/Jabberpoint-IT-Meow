import javax.swing.*;

public class GoToCommand extends Command
{

    public GoToCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute()
    {
        String pageNumberStr = JOptionPane.showInputDialog((Object)MenuController.PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setSlideNumber(pageNumber - 1);
    }
}
