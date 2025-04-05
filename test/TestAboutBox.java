import java.awt.Frame;

public class TestAboutBox {
    private static boolean wasShown = false;
    private static Frame lastFrame = null;

    public static void show(Frame parent) {
        wasShown = true;
        lastFrame = parent;
    }

    public static boolean wasShown() {
        return wasShown;
    }

    public static Frame getLastFrame() {
        return lastFrame;
    }

    public static void reset() {
        wasShown = false;
        lastFrame = null;
    }
} 