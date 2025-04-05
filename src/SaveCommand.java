import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SaveCommand extends Command
{
    private Accessor accessor;
    private String fileName;

    public SaveCommand(Presentation presentation, Frame parent)
    {
        this.presentation = presentation;
        this.parent = parent;
        this.fileName = this.presentation.getTitle() + ".xml";
    }

    @Override
    public void execute()
    {
        Accessor xmlAccessor = new XMLAccessor();
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try (FileWriter writer = new FileWriter(fileName)){
            xmlAccessor.saveFile(presentation, MenuController.SAVEFILE);
            writer.write("Saved on: " + formattedDate + "\n");
            System.out.println("Saved on: " + formattedDate);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, MenuController.IOEX + exc,
                    MenuController.SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }
    
}
