import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpenCommand extends Command
{
    private Accessor accessor;
    private String fileName;

    public OpenCommand(Presentation presentation, Frame parent)
    {
        this.presentation = presentation;
        this.parent = parent;
    }

    public void execute() {
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, MenuController.TESTFILE);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, MenuController.IOEX + exc,
                    MenuController.LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }
}
