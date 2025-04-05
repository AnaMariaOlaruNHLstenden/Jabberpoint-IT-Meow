package Architecture;

import FactoryMethodAndComposite.Presentation;
import FactoryMethodAndComposite.Slide;

public interface SlideViewer
{
    public default void update(Presentation presentation, Slide data) {

    }
}
