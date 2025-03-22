public class TextItemCreator extends SlideItemFactory{
    
    public TextItemCreator()
    {
    }
    
    @Override
    public SlideItem createSlideItem(int level, String content) {
        return new TextItem(level, content);
    }
}
