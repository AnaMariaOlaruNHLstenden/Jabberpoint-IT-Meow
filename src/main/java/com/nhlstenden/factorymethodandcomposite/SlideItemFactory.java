package com.nhlstenden.factorymethodandcomposite;

public abstract class SlideItemFactory{
    
    public SlideItemFactory()
    {
    }
    
    public abstract SlideItem createSlideItem(int level, String content);
}
