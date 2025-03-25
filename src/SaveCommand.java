import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class SaveCommand extends Command
{
    private Accessor accessor;
    private String fileName;

    public SaveCommand(Presentation presentation, Frame parent)
    {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute()
    {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, MenuController.SAVEFILE);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, MenuController.IOEX + exc,
                    MenuController.SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }
}
