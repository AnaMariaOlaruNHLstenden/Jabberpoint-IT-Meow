package com.nhlstenden.ui.view;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import com.nhlstenden.style.StyleManager;

public interface Drawable {

    void draw(Graphics g, int x, int y, float scale, ImageObserver observer, StyleManager styleManager);
}