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

    private final String label;

    MenuCommandKey(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
