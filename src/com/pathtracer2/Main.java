package com.pathtracer2;

import java.util.ArrayList;

public class Main {
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	public static final int NUM_SECONDARY_RAYS = 16;
	public static final int NUM_PRIMARY_RAYS = 16;
	
	public static void main2(String[] args) {

		Output output = new Output(WIDTH, HEIGHT);
		
		ArrayList<Sphere> spheres = new ArrayList<Sphere>();
		//spheres.add(new Sphere(new Vector(1.0, 0.0, 5.0), 0.7, 10000.0));
		//spheres.add(new Sphere(new Vector(-1.0, 0.0, 5.0), 0.7, 0.0));
		spheres.add(new Sphere(new Vector(0.0, 1.0, 3.0), 0.2, 10000.0));
		spheres.add(new Sphere(new Vector(0.0, -1.0, 3.0), 0.5, 0.0));
		
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
				double px = ((double)i - (double)WIDTH / 2) / (double)WIDTH;
				double py = ((double)j - (double)HEIGHT / 2) / (double)HEIGHT;

				Ray ray = new Ray(new Vector(0, 0, 0), new Vector(px, py, 1));
				
				double radiance = 0;
				for(int k = 0; k < NUM_PRIMARY_RAYS; k++) {
					radiance += Tracer.traceRay(ray, spheres, 0);
				}
				radiance /= NUM_PRIMARY_RAYS;
				
				//output.writePixel(i, j, (int)Math.pow(radiance, 0.5));
				output.writePixel(i, j, (int)radiance);
			}
			System.out.println(Math.floor(i * 100 / WIDTH) + "%");
		}
		
		output.writeToFile("output.png");
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 30; i++) {
			System.out.print(Tracer.randomInHemisphere(new Vector(10.0, 10.0, 10.0)).toString());
		}
	}
}
