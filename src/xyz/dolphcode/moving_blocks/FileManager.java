package xyz.dolphcode.moving_blocks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileManager {
	
	public static final String IMPORT_PATH = "C:\\Basti\\MovingTextures\\block";
	public static final String EXPORT_PATH = "C:\\Basti\\MovingTextures\\block_completed";
	
	private static final String ANIM_TEXT = "{\"animation\":{}}";
	
	public static String[] getPaths() {
		String[] paths;
		File dir = new File(IMPORT_PATH);
		paths = dir.list();
		for (int i = 0; i < paths.length; i++)
			paths[i] = IMPORT_PATH + "\\" + paths[i];
		return paths;
	}
	
	public static String[] getNames() {
		String[] paths;
		File dir = new File(IMPORT_PATH);
		paths = dir.list();
		return paths;
	}
	
	public static void exportAnimation(BufferedImage img, String name) {
		
		try {
			File animFile = new File(EXPORT_PATH + "\\" + name + ".mcmeta");
			animFile.createNewFile();
			FileWriter writer = new FileWriter(EXPORT_PATH + "\\" + name + ".mcmeta");
			writer.write(ANIM_TEXT);
			writer.close();
			
			ImageIO.write(img, "png", new File(EXPORT_PATH + "\\" + name));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
