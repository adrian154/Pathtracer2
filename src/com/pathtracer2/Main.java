package com.pathtracer2;

public class Main {
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	
	public static void main(String[] args) {
		
		Output output = new Output(WIDTH, HEIGHT);
		Scene scene = new Scene();
		
		Material red = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 0.0, 0.0), 1.0);
		Material white = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 1.0, 1.0), 0.0);
		Material source = new Material(new TraceColor(400.0, 400.0, 400.0), new TraceColor(1.0, 1.0, 1.0), 0.0);
		
		//scene.spheres.add(new Sphere(new Vector(0.0, -1.2, 3.0), 0.4, source));
		scene.spheres.add(new Sphere(new Vector(0.0, -0.2, 3.0), 0.7, red));
		
		Tracer.traceScene(scene, output);
	}
	
}
