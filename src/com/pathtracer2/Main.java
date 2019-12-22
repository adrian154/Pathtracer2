package com.pathtracer2;

import java.util.ArrayList;

public class Main {
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	public static final int NUM_SECONDARY_RAYS = 64;
	public static final int NUM_PRIMARY_RAYS = 64;
	
	public static void main(String[] args) {
		
		Output output = new Output(WIDTH, HEIGHT);
		
		/* Put some spheres in scene */
		ArrayList<Sphere> spheres = new ArrayList<Sphere>();
		spheres.add(new Sphere(new Vector(0.0, -0.6, 3.0), 0.1, 10000.0));
		spheres.add(new Sphere(new Vector(0.0, 0.0, 3.0), 0.2, 10.0));
		spheres.add(new Sphere(new Vector(0.0, 50.5, 0.0), 50.0, 10.0));
		
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
				
				double px = ((double)i - (double)WIDTH / 2) / (double)WIDTH;
				double py = ((double)j - (double)HEIGHT / 2) / (double)HEIGHT;
				Ray ray = new Ray(new Vector(0, 0, 0), new Vector(px, py, 1));
				
				/* Do primary rays */
				double radiance = 0;
				for(int k = 0; k < NUM_PRIMARY_RAYS; k++) {
					radiance += Tracer.traceRay(ray, spheres, 0, -1);
				}
				radiance /= NUM_PRIMARY_RAYS;

				/* Write pixel */
				output.writePixel(i, j, (int)radiance);
	
			}
			
			/* Print progress */
			System.out.println(Math.floor(i * 100 / WIDTH) + "%");
		}
		
		long finishTime = System.currentTimeMillis();
		long elapsed = finishTime - startTime;
		System.out.println("Took " + (elapsed / 1000) + " seconds to render.");
		
		output.writeToFile("output.png");
	}
	
}
