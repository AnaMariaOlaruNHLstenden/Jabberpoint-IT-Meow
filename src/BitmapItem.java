import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/** <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items have the responsibility to draw themselves.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class BitmapItem extends SlideItem {
	private BufferedImage bufferedImage;
	private String imageName;
	
	protected static final String FILE = "File ";
	protected static final String NOTFOUND = " not found";
	
	// level is equal to item-level; name is the name of the file with the Image
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		
		// Step 1: Null or empty check (early exit)
		if (imageName == null || imageName.isEmpty()) {
			throw new IllegalArgumentException("File name cannot be null or empty.");
		}
		
		// Step 2: Validate file extension
		if (!isValidImageExtension(imageName)) {
			throw new IllegalArgumentException("Invalid file type for BitmapItem: " + imageName);
		}
		
		// Step 3: Attempt to load the image file
		try {
			bufferedImage = ImageIO.read(new File(imageName));
			if (bufferedImage == null) {
				throw new IllegalArgumentException("Unable to load image: " + imageName);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not read the file: " + imageName, e);
		}
	}
	
	// Helper method to check if the file extension is valid
	private boolean isValidImageExtension(String fileName) {
		String lowerCaseFileName = fileName.toLowerCase();
		return lowerCaseFileName.endsWith(".png")
				|| lowerCaseFileName.endsWith(".jpg")
				|| lowerCaseFileName.endsWith(".jpeg")
				|| lowerCaseFileName.endsWith(".bmp")
				|| lowerCaseFileName.endsWith(".gif");
	}
	
	// give the filename of the image
	public String getName() {
		return imageName;
	}
	
	// give the  bounding box of the image
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, StyleManager styleManager) {
		Style myStyle = styleManager.getStyle(getLevel());
		return new Rectangle((int) (myStyle.getIndent() * scale), 0, (int) (bufferedImage.getWidth(observer) * scale), ((int) (myStyle.getLeading() * scale)) + (int) (bufferedImage.getHeight(observer) * scale));
	}
	
	// draw the image
	public void draw(Graphics g, int x, int y, float scale, ImageObserver observer, StyleManager styleManager) {
		Style myStyle = styleManager.getStyle(getLevel());
		int width = x + (int) (myStyle.getIndent() * scale);
		int height = y + (int) (myStyle.getLeading() * scale);
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale), (int) (bufferedImage.getHeight(observer)*scale), observer);
	}
	
	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}