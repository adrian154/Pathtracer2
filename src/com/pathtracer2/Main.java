package com.pathtracer2;

public class Main {
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	
	public static void main(String[] args) {
		
		Output output = new Output(WIDTH, HEIGHT);
		Scene scene = new Scene();

		double box = 2;
		
		
		for(int i = 0; i < 8; i++) {
			Material material;
			double radius;
			
			if(Math.random() < 0.5) {
				material = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(Math.random(), Math.random(), Math.random()), 0.0);
				radius = 0.3;
			} else if(Math.random() < 0.5) {
				material = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(Math.random(), Math.random(), Math.random()), 0.75);
				radius = 0.3;
			} else {
				TraceColor color = new TraceColor(Math.random(), Math.random(), Math.random());	
				material = new Material(color.times(300), color, 1.0);
				radius = 0.2;
			}
			
			Sphere sphere = new Sphere(new Vector(Math.random() * box - box / 2, Math.random() * box - box / 2, Math.random() * box - box / 2 + 3), radius, material);
			scene.objects.add(sphere);

		}
		
		
		Material mat = new Material(new TraceColor(10.0, 10.0, 10.0), new TraceColor(1.0, 1.0, 1.0), 0.0);
		Plane plane = new Plane(new Vector(0.0, 1.0, 0.0), new Vector(0.0, 1.0, 0.0), mat);	
		scene.objects.add(plane);
		
		Tracer.traceScene(scene, output);
	}
	
}
