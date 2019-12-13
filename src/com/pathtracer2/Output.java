package com.pathtracer2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Output {
	public BufferedImage image;
	public int width;
	public int height;
	
	public Output(int width, int height) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.width = width;
		this.height = height;
	}
	
	public void writePixel(int x, int y, int value) {
		/* Clamp value to 0 - 0xFF */
		int val = Math.min(0xFF, Math.max(0, value));
		int rgb = (val << 16) | (val << 8) | (val);
		image.setRGB(x, y, rgb);	
	}
	
	public void writeToFile(String filename) {
		File imageFile = new File(filename);
		try {
			ImageIO.write(image, "png", imageFile);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
}
