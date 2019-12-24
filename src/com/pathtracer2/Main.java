package com.pathtracer2;

import java.util.ArrayList;

public class Main {
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	
	public static void main(String[] args) {
		
		Output output = new Output(WIDTH, HEIGHT);
		Scene scene = new Scene();
		
		/* Make some materials */
		Material lightSourceMat = new Material(3000.0, 0.0);		// Non-reflective with high emission
		Material darkMat = new Material(0.0, 0.5);					// Non-emissive with low reflection
		Material lightMat = new Material(0.0, 1.0);					// Non-emissive with high reflection
		
		/* Add some spheres to the scene */
		scene.spheres.add(new Sphere(new Vector(0.0, -0.7, 3.0), 0.3, lightSourceMat));
		scene.spheres.add(new Sphere(new Vector(-0.4, 0.0, 3.0), 0.3, darkMat));
		scene.spheres.add(new Sphere(new Vector(0.4, 0.0, 3.0), 0.3, lightMat));
		
		Tracer.traceScene(scene, output);
	}
	
}
