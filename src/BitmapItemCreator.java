public class BitmapItemCreator extends SlideItemFactory{
    
    public BitmapItemCreator()
    {
    }
    
    @Override
    public SlideItem createSlideItem(int level, String content) {
        return new BitmapItem(level, content);
    }
}
