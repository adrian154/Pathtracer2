package com.pathtracer2;

public class Main {
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	
	public static void main(String[] args) {
		
		Output output = new Output(WIDTH, HEIGHT);
		Scene scene = new Scene();
		
		/*
		Material lightSourceMat = new Material(3000.0, 0.0);		// Non-reflective with high emission
		Material darkMat = new Material(0.0, 0.5);					// Non-emissive with low reflection
		Material lightMat = new Material(0.0, 1.0);					// Non-emissive with high reflection
		Material ambientMat = new Material(200.0, 0.0);

		scene.spheres.add(new Sphere(new Vector(0.0, 0.0, 3.0), 0.5, lightMat));
		scene.spheres.add(new Sphere(new Vector(0.0, 10000.7, 3.0), 10000, darkMat));
		scene.spheres.add(new Sphere(new Vector(0.0, 0.0, 3.0), 100, ambientMat));
		*/
		
		TraceColor color = new TraceColor(
			Math.random(),
			Math.random(),
			Math.random()
		);
		
		for(int i = 0; i < 32; i++) {
			scene.spheres.add(new Sphere(
				new Vector(
					Math.random() * 3 - 1.5,
					Math.random() * 3 - 1.5,
					Math.random() * 3 - 1.5 + 3
				),
				Math.random() * 0.2 + 0.1,
				new Material(
					Math.random() > 0.5 ? color.times(1000) : new TraceColor(0, 0, 0),
					color
				)
			));
		}
		
		Tracer.traceScene(scene, output);
	}
	
}
