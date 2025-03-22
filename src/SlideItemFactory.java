public abstract class SlideItemFactory{
    
    public SlideItemFactory()
    {
    }
    
    abstract SlideItem createSlideItem(int level, String content);
}
