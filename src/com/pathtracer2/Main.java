package com.pathtracer2;

import java.util.ArrayList;

public class Main {
	public static final int WIDTH = 256;
	public static final int HEIGHT = 256;
	
	public static void main(String[] args) {
		Output output = new Output(WIDTH, HEIGHT);
		
		ArrayList<Sphere> spheres = new ArrayList<Sphere>();
		spheres.add(new Sphere(new Vector(1.0, 0.0, 5.0), 0.7, 100.0));
		spheres.add(new Sphere(new Vector(-1.0, 0.0, 5.0), 0.7, 0.0));
		
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
				double px = ((double)i - (double)WIDTH / 2) / (double)WIDTH;
				double py = ((double)j - (double)HEIGHT / 2) / (double)HEIGHT;

				Ray ray = new Ray(new Vector(0, 0, 0), new Vector(px, py, 1));
				double radiance = Tracer.traceRay(ray, spheres, 0);
				output.writePixel(i, j, (int)radiance);
			}
		}
		
		output.writeToFile("output.png");
	}
	
}
