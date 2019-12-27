package com.pathtracer2;

import java.awt.image.BufferedImage;

public class TexturedPlane extends Plane {
	
	public BufferedImage texture;
	public double tilingSize;
	
	public TexturedPlane(Vector point, Vector normal, Material material, BufferedImage texture, double tilingSize) {
		super(point, normal, material);
		
		this.texture = texture;
		this.tilingSize = tilingSize;
	}
	
}
