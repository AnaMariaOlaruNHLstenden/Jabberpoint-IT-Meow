package Architecture;

import Style.StyleManager;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface Drawable {

    void draw(Graphics g, int x, int y, float scale, ImageObserver observer, StyleManager styleManager);
}