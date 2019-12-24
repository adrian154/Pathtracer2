package com.pathtracer2;

public class Main {
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	
	public static void main(String[] args) {
		
		Output output = new Output(WIDTH, HEIGHT);
		Scene scene = new Scene();

		for(int i = 0; i < 64; i++) {
			Material material = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(Math.random(), Math.random(), Math.random()), 1.0, 0.0);
			Sphere sphere = new Sphere(new Vector(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5 + 3), 0.2, material);
			scene.spheres.add(sphere);
		}

		Tracer.traceScene(scene, output);
	}
	
}
