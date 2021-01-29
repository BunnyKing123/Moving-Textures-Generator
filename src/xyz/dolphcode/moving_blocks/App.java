package xyz.dolphcode.moving_blocks;

import java.awt.image.BufferedImage;

public class App {
	
	public static void main(String... args) {
		String[] paths = FileManager.getPaths();
		String[] filenames = FileManager.getNames();
		for (int i = 0; i < paths.length; i++) {
			System.out.println(paths[i]);
			BufferedImage img = ImgManipulator.animationMaker(paths[i]);
			FileManager.exportAnimation(img, filenames[i]);
		}
	}

}
