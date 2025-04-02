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
        // Loop until valid input or cancellation
        while (true) {
            int totalSlides = presentation.getSize();

            // Check if there are no slides
            if (totalSlides == 0) {
                JOptionPane.showMessageDialog(parent,
                        "There are no slides in the presentation.",
                        "Empty Presentation",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            // Show input dialog with current valid range
            String pageNumberStr = JOptionPane.showInputDialog(
                    parent,
                    "Enter slide number (1 - " + totalSlides + "):",
                    "Go To Slide",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Exit if user cancels the dialog
            if (pageNumberStr == null) {
                return;
            }

            try {
                int pageNumber = Integer.parseInt(pageNumberStr.trim());

                // Validate slide number range
                if (pageNumber >= 1 && pageNumber <= totalSlides) {
                    presentation.setSlideNumber(pageNumber - 1); // Adjust for 0-based index
                    return; // Exit loop on valid input
                } else {
                    JOptionPane.showMessageDialog(
                            parent,
                            "Please enter a number between 1 and " + totalSlides,
                            "Invalid Number",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        parent,
                        "Invalid input. Please enter a numeric value.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
