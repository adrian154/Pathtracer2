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
		image = new BufferedImage(width * 2, height * 2, BufferedImage.TYPE_INT_RGB);
		this.width = width;
		this.height = height;
	}
	
	public void writePixel(int x, int y, int r, int g, int b) {
	
		/* Clamp value to 0 - 0xFF */
		r = Math.min(0xFF, Math.max(0, r));
		g = Math.min(0xFF, Math.max(0, g));
		b = Math.min(0xFF, Math.max(0, b));
		
		/* Write actual rgb value */
		int rgb = (r << 16) | (g << 8) | (b);
		image.setRGB(x * 2, y * 2, rgb);
		image.setRGB(x * 2 + 1, y * 2, rgb);
		image.setRGB(x * 2, y * 2 + 1, rgb);
		image.setRGB(x * 2 + 1, y * 2 + 1, rgb);
	
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
