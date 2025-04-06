package com.nhlstenden.factorymethodandcomposite;

public class TextItemCreator extends SlideItemFactory{
    
    public TextItemCreator()
    {
    }
    
    @Override
    public SlideItem createSlideItem(int level, String content) {
        if (content != null && level >= 0)
        {
            return new TextItem(level, content);
        }
        else {
            throw new IllegalArgumentException("Content must not be empty and level must not be less than 0");
        }
    }
}
