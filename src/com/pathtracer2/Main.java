package com.pathtracer2;

import java.util.ArrayList;

public class Main {
	public static final int WIDTH = 360;
	public static final int HEIGHT = 360;
	
	
	public static void main(String[] args) {

		Output output = new Output(WIDTH, HEIGHT);
		Scene scene = new Scene();

		/* World objects. */
		ArrayList<WorldObject> objects = new ArrayList<WorldObject>();
		
		/* Area that spheres can occupy. */
		double box = 2.0;

		for(int i = 0; i < 16; i++) {
			Material material;
			double radius;
			
			if(Math.random() < (1.0/3.0)) {
				material = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(Math.random(), Math.random(), Math.random()), 0.0);
				radius = 0.2;
			} else if(Math.random() < 0.6) {
				material = new Material(new TraceColor(0.0, 0.0, 0.0), new TraceColor(Math.random(), Math.random(), Math.random()), 0.75);
				radius = 0.15;
			} else {
				TraceColor color = new TraceColor(Math.random(), Math.random(), Math.random());	
				material = new Material(color.times(700), color, 1.0);
				radius = 0.1;
			}

			Sphere sphere = new Sphere(new Vector(Math.random() * box - box / 2, Math.random() * box - box / 2, Math.random() * box - box / 2 + 6), radius, material);
			objects.add(sphere);

		}
		
		/* Generate frames. */
		for(int i = 0; i < 180; i++) {
			
			/* Reset scene objects. */
			scene.objects = new ArrayList<WorldObject>();
			
			/* Loop through objects and regenerate scene objects. */
			for(int j = 0; j < objects.size(); j++) {
				
				WorldObject object = objects.get(j);
				
				if(object instanceof Sphere) {
					
					/* Copy old sphere's properties into scene sphere */
					Sphere oldSphere = (Sphere)object;
					Sphere newSphere = new Sphere();
					newSphere.radius = oldSphere.radius;
					newSphere.material = oldSphere.material;
					
					/* Rotate */
					newSphere.center = Transform.transform(new Vector(oldSphere.center.x, oldSphere.center.y, oldSphere.center.z - 6), (2 * Math.PI / 180) * i, 0, 0);
					newSphere.center.z += 6;
					
					/* Add */
					scene.objects.add(newSphere);
				
				}
				
			}

			/* Draw frame. */
			Tracer.traceScene(scene, output);
			output.writeToFile(i + ".png");
			
		}
		
	}
	
}