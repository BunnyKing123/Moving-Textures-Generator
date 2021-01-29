package xyz.dolphcode.moving_blocks;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgManipulator {
	
	public static BufferedImage animationMaker(String path) {
		// Get file at path
		File filePath = new File(path);
        BufferedImage texture = null;
        
        // Get original texture from path
		try {
			texture = ImageIO.read(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get starting column and # of columns
		int[] data = getColumns(texture);
		int x = data[0];
		int xLen = data[1];
		
		// Create a blank buffered image
		//BufferedImage animated = new BufferedImage(16, 16 * 16, BufferedImage.TYPE_INT_ARGB);
		BufferedImage animated = new BufferedImage(16, 16 * xLen, BufferedImage.TYPE_INT_ARGB);
		
	    // Draw the first frame
		Graphics2D bGr = animated.createGraphics();
		bGr.drawImage(texture, 0, 0, null);
		
		// Draw the frames after the first
		/*
		for (int i = 1; i < 16; i++) {
		    bGr.drawImage(texture, 16 - i, 16 * i, 16, 16 * (i + 1), 
		    			  0, 0, 0 + i, 16, null);
		    bGr.drawImage(texture, 0, 16 * i, 16 - i, 16 * (i + 1), 
	    			  i, 0, 16, 16, null);
		}
		*/
		for (int i = 1; i < xLen; i++) {
		    bGr.drawImage(texture, (x + xLen) - i, 16 * i, (x + xLen), 16 * (i + 1), 
		    			  x, 0, x + i, 16, null);
		    bGr.drawImage(texture, x, 16 * i, (x + xLen) - i, 16 * (i + 1), 
	    			  x + i, 0, (x + xLen), 16, null);
		}
		

	    // Return the buffered image
	    return animated;
	}
	
	private static int[] getColumns(BufferedImage img) {
		int columnStart = 0;
		int columns = 16;
		int pixel;
		OUTER: for (int x = 0; x < 8; x++) {
			INNER: for (int y = 0; y < 16; y++) {
				pixel = img.getRGB(x, y);
				if ((pixel>>24) != 0x00) {
					columnStart = x;
					columns = 2 * (8 - x);
					break OUTER;
				}
			}
		}
		int[] data = {columnStart, columns};
		return data;
	}

}
