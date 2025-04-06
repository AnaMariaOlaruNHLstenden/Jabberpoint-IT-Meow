package com.nhlstenden.app;

import com.nhlstenden.factorymethodandcomposite.Presentation;
import com.nhlstenden.factorymethodandcomposite.Slide;
import com.nhlstenden.factorymethodandcomposite.SlideItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoPresentationTest {
    private DemoPresentation demoPresentation;
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        demoPresentation = new DemoPresentation();
        presentation = new Presentation();
    }

    @Test
    void loadFile_ShouldSetCorrectTitle() {
        demoPresentation.loadFile(presentation, "dummy");
        assertEquals("Demo Presentation", presentation.getTitle());
    }

    @Test
    void loadFile_ShouldCreateThreeSlides() {
        demoPresentation.loadFile(presentation, "dummy");
        assertEquals(3, presentation.getSize());
    }

    @Test
    void loadFile_FirstSlideShouldHaveCorrectTitle() {
        demoPresentation.loadFile(presentation, "dummy");
        Slide firstSlide = presentation.getSlide(0);
        assertEquals("JabberPoint", firstSlide.getTitle());
    }

    @Test
    void loadFile_FirstSlideShouldHaveCorrectNumberOfItems() {
        demoPresentation.loadFile(presentation, "dummy");
        Slide firstSlide = presentation.getSlide(0);
        assertEquals(10, firstSlide.getSize());
    }

    @Test
    void loadFile_SecondSlideShouldHaveCorrectTitle() {
        demoPresentation.loadFile(presentation, "dummy");
        Slide secondSlide = presentation.getSlide(1);
        assertEquals("Demonstration of levels and styles", secondSlide.getTitle());
    }

    @Test
    void loadFile_ThirdSlideShouldHaveCorrectTitle() {
        demoPresentation.loadFile(presentation, "dummy");
        Slide thirdSlide = presentation.getSlide(2);
        assertEquals("The third slide", thirdSlide.getTitle());
    }

    @Test
    void saveFile_ShouldThrowIllegalStateException() {
        assertThrows(IllegalStateException.class, () -> {
            demoPresentation.saveFile(presentation, "dummy");
        });
    }
} 