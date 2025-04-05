package Command;

public enum MenuCommandKey
{
    ABOUT("About"),
    FILE("File"),
    EXIT("Exit"),
    GOTO("Go to"),
    HELP("Help"),
    NEW("New"),
    NEXT("Next"),
    OPEN("Open"),
    PAGENR("Page number?"),
    PREV("Prev"),
    SAVE("Save"),
    VIEW("View");

    private final String LABEL;

    MenuCommandKey(String label) {
        this.LABEL = label;
    }

    public String getLabel() {
        return LABEL;
    }
}
