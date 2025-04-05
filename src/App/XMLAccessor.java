package App;

import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import FactoryMethodAndComposite.*;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


/** App.XMLAccessor, reads and writes XML files
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLAccessor extends Accessor {
	
    /** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";
    
    /** namen van xml tags of attributen */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    
    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
    
    private String getTitle(Element element, String tagName) {
    	NodeList titles = element.getElementsByTagName(tagName);
    	return titles.item(0).getTextContent();
    	
    }

	public void loadFile(Presentation presentation, String filename) throws IOException {
		int slideNumber, itemNumber, max = 0, maxItems = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();    
			Document document = builder.parse(new File(filename)); // Create a JDOM document
			Element doc = document.getDocumentElement();
			presentation.setTitle(getTitle(doc, SHOWTITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			max = slides.getLength();
			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);
				Slide slide = new Slide();
				slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
				presentation.append(slide);
				
				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				maxItems = slideItems.getLength();
				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
					Element item = (Element) slideItems.item(itemNumber);
					loadSlideItem(slide, item);
				}
			}
		} 
		catch (IOException iox) {
			System.err.println(iox.toString());
		}
		catch (SAXException sax) {
			System.err.println(sax.getMessage());
		}
		catch (ParserConfigurationException pcx) {
			System.err.println(PCE);
		}	
	}

	protected void loadSlideItem(Slide slide, Element item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println(NFE);
			}
		}
		String type = attributes.getNamedItem(KIND).getTextContent();
		if (TEXT.equals(type)) {
			slide.append(new TextItem(level, item.getTextContent()));
		}
		else {
			if (IMAGE.equals(type)) {
				slide.append(new BitmapItem(level, item.getTextContent()));
			}
			else {
				System.err.println(UNKNOWNTYPE);
			}
		}
	}

	public void saveFile(Presentation presentation, String filename) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(filename));
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");
		out.print("<showtitle>");
		out.print(presentation.getTitle());
		out.println("</showtitle>");
		for (int slideNumber=0; slideNumber<presentation.getSize(); slideNumber++) {
			Slide slide = presentation.getSlide(slideNumber);
			out.println("<slide>");
			out.println("<title>" + slide.getTitle() + "</title>");
			Vector<SlideComponent> slideItems = slide.getSlideItems();
			for (int itemNumber = 0; itemNumber<slideItems.size(); itemNumber++) {
				SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
				out.print("<item kind="); 
				if (slideItem instanceof TextItem) {
					out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
					out.print( ( (TextItem) slideItem).getText());
				}
				else {
					if (slideItem instanceof BitmapItem) {
						out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
						out.print( ( (BitmapItem) slideItem).getName());
					}
					else {
						System.out.println("Ignoring " + slideItem);
					}
				}
				out.println("</item>");
			}
			out.println("</slide>");
		}
		out.println("</presentation>");
		out.close();
	}
}
