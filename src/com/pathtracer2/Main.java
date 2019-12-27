package com.pathtracer2;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Main {
	public static final int WIDTH = 720;
	public static final int HEIGHT = 720;
	
	public static BufferedImage texture;
	public static BufferedImage texture2;
	
	public static void main(String[] args) {
		
		try {
			texture = ImageIO.read(new File("texture.png"));
			texture2 = ImageIO.read(new File("texture2.png"));
		} catch(Exception exception) {
			
		}
		
		Output output = new Output(WIDTH, HEIGHT);
		Scene scene = new Scene();
		double boxHeight = 2.0;
		double boxDepth = 10.0;
		double boxWidth = 2.0;
		
		Material red = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 0.0, 0.0), 0.0);
		Material white = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 1.0, 1.0), 0.0);
		Material green = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(0.0, 1.0, 0.0), 0.0);
		Material yellow = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 1.0, 0.0), 0.0);
		Material sky = new Material(new TraceColor(300.0, 300.0, 300.0), new TraceColor(1.0, 1.0, 1.0), 0.0);
		
		Material source = new Material(new TraceColor(4000.0, 4000.0, 4000.0), new TraceColor(1.0, 1.0, 1.0), 0.0);
		
		Material mirror = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(1.0, 1.0, 1.0), 1.0);
		
		Plane floor = new Plane(new Vector(0.0, -boxHeight / 2, 0.0), new Vector(0.0, 1.0, 0.0), white);
		Plane ceiling = new Plane(new Vector(0.0, boxHeight / 2, 0.0), new Vector(0.0, -1.0, 0.0), yellow); 
		Plane front = new Plane(new Vector(0.0, 0.0, boxDepth / 2), new Vector(0.0, 0.0, -1.0), sky);
		Plane rear = new Plane(new Vector(0.0, 0.0, -1.0), new Vector(0.0, 0.0, 1.0), white);
		Plane left = new Plane(new Vector(-boxWidth / 2, 0.0, 0.0), new Vector(1.0, 0.0, 0.0), green);
		Plane right = new Plane(new Vector(boxWidth / 2, 0.0, 0.0), new Vector(-1.0, 0.0, 0.0), red);

		//Sphere light = new Sphere(new Vector(0.0, 1.93, 3.0), 1.0, source);
		
		Sphere sph1 = new Sphere(new Vector(0.3, -0.75, 3.0), 0.25, white);
		Sphere sph2 = new Sphere(new Vector(-0.3, -0.75, 3.0), 0.25, mirror);
		
		scene.objects.add(floor);
		scene.objects.add(ceiling);
		scene.objects.add(front);
		scene.objects.add(rear);
		scene.objects.add(left);
		scene.objects.add(right);
			
		scene.objects.add(sph1);
		scene.objects.add(sph2);

		Tracer.traceScene(scene, output);
		//output.writeToFile("output.png");
		
	}
	
}