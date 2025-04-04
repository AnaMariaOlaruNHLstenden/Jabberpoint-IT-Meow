import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

class StyleManager {
    private final Map<Integer, Style> STYLES = new HashMap<>();
    
    public StyleManager() {
        // Default styles
        addStyle(0, new Style(0, Color.RED, 48, 20));
        addStyle(1, new Style(20, Color.BLUE, 40, 10));
        addStyle(2, new Style(50, Color.BLACK, 36, 10));
        addStyle(3, new Style(70, Color.BLACK, 30, 10));
        addStyle(4, new Style(90, Color.BLACK, 24, 10));
    }
    
    public void addStyle(int level, Style style) {
        STYLES.put(level, style);
    }
    
    public Style getStyle(int level) {
        return STYLES.getOrDefault(level, STYLES.get(STYLES.size() - 1));
    }
}