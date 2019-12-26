package com.pathtracer2;

public class Main {
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	public static void main(String[] args) {
		
		Output output = new Output(WIDTH, HEIGHT);
		Scene scene = new Scene();

		double boxHeight = 2.0;
		double boxDepth = 10.0;
		double boxWidth = 2.0;
		
		Material red = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 0.0, 0.0), 0.0);
		Material green = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(0.0, 1.0, 0.0), 0.0);
		Material white = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 1.0, 1.0), 0.0);
		Material blue = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(0.0, 0.0, 1.0), 0.0);
		Material yellow = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 1.0, 0.0), 0.0);
		Material source = new Material(new TraceColor(1000.0, 1000.0, 1000.0), new TraceColor(1.0, 1.0, 1.0), 0.0);
		
		Plane floor = new Plane(new Vector(0.0, boxHeight / 2, 0.0), new Vector(0.0, 1.0, 0.0), green);
		Plane ceiling = new Plane(new Vector(0.0, -boxHeight / 2, 0.0), new Vector(0.0, -1.0, 0.0), red); 
		Plane front = new Plane(new Vector(0.0, 0.0, boxDepth / 2), new Vector(0.0, 0.0, -1.0), white);
		Plane rear = new Plane(new Vector(0.0, 0.0, -boxDepth / 2), new Vector(0.0, 0.0, 1.0), white);
		Plane left = new Plane(new Vector(-boxWidth / 2, 0.0, 0.0), new Vector(1.0, 0.0, 0.0), blue);
		Plane right = new Plane(new Vector(boxWidth / 2, 0.0, 0.0), new Vector(-1.0, 0.0, 0.0), yellow);
		Sphere sourceSph = new Sphere(new Vector(0.0, 0.0, 6.0), 1.0, source);
		
		scene.objects.add(floor);
		scene.objects.add(ceiling);
		scene.objects.add(front);
		scene.objects.add(rear);
		scene.objects.add(left);
		scene.objects.add(right);
		scene.objects.add(sourceSph);
		
		/* Draw frame. */
		Tracer.traceScene(scene, output);
		output.writeToFile("output.png");
		
	}
	
}