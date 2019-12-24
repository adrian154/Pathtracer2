package com.pathtracer2;

import java.util.ArrayList;

public class Tracer {
	
	public static final int NUM_SECONDARY_RAYS = 10;
	public static final int NUM_PRIMARY_RAYS = 10;
	public static final int NUM_BOUNCES = 4;
	
	public static TraceColor AMBIENT = new TraceColor(24.0, 24.0, 24.0);
	
	public static double FLOOR = 1.0;
	
	/* Generate random vector in hemisphere */
	public static Vector randomInHemisphere() {
		double theta = 2 * Math.PI  * Math.random();
		double phi = Math.acos(1 - 2 * Math.random());
		double x = Math.sin(phi) * Math.cos(theta);
		double y = Math.abs(Math.cos(phi));
		double z = Math.sin(theta) * Math.sin(phi);
		return new Vector(x, y, z);
	}
	
	/* Generate random vector in hemisphere. */
	public static Vector randomInHemisphere(Vector normal) {
		
		/* Normalize input vector to be safe */
		normal = normal.normalize();
		
		/* First, generate random vector */
		Vector randomVector = randomInHemisphere();
		
		/* We need to translate this vector from the local coordinate space (where Z=(0,0,1)) to a global coordinate space where Z=Normal */
		/* U,V,W = basis vectors of local coord space (V=Normal) */
		Vector V = normal;
		Vector U = Vector.getOrthagonal(V);
		Vector W = Vector.cross(U, V);
		
		/* Convert randomVector to global cartesian coords */
		Vector result = new Vector(
			randomVector.x * U.x + randomVector.y * V.x + randomVector.z * W.x,
			randomVector.x * U.y + randomVector.y * V.y + randomVector.z * W.y,
			randomVector.x * U.z + randomVector.y * V.z + randomVector.z * W.z
		);
		
		return result;
	}
	
	/* Trace primary ray. */
	public static TraceColor traceRay(Ray ray, ArrayList<Sphere> spheres, int bounces, int originIndex) {
		
		if(bounces > NUM_BOUNCES) {
			return new TraceColor(0.0, 0.0, 0.0);
		}
		
		Intersection intersection = IntersectionTester.getIntersection(ray, spheres, originIndex);
	
		Material material = intersection.sphere.material;
		Vector normal = Vector.sub(intersection.point, intersection.sphere.center).normalize();
		
		double distance = intersection.distance;
		double floorDistance = (Tracer.FLOOR - ray.origin.y) / ray.direction.y;
		
		if(floorDistance < intersection.distance && floorDistance > 0) {
			distance = floorDistance;
			intersection.point = ray.point(floorDistance);
			
			boolean grid = Math.sin(intersection.point.x * 3) > 0 ^ Math.sin(intersection.point.z * 3) > 0;
			material = new Material(new TraceColor(0.0, 0.0, 0.0), grid ? new TraceColor(1.0, 1.0, 1.0) : new TraceColor(0.0, 0.0, 0.0), 0.0);
			normal = new Vector(0.0, 1.0, 0.0);
		}
		
		if(distance < Double.POSITIVE_INFINITY) {
		
			TraceColor radiance = material.emission;
			
			/* Do some samples. */
			TraceColor incomingLight = new TraceColor(0.0, 0.0, 0.0);

			for(int i = 0; i < NUM_SECONDARY_RAYS; i++) {

				Vector newDirection;
				if(Math.random() < material.probReflective) {
					newDirection = Vector.sub(ray.direction, Vector.multiply(normal, 2 * Vector.dot(ray.direction, normal)));
				} else {
					newDirection = randomInHemisphere(normal).normalize();
				}
				
				Ray newRay = new Ray(intersection.point, newDirection);
				incomingLight = incomingLight.plus(traceRay(newRay, spheres, bounces + 1, intersection.sphereIndex).times(Vector.dot(newDirection, normal)));
			}
			
			incomingLight = incomingLight.div(NUM_SECONDARY_RAYS);
			incomingLight = incomingLight.times(material.reflection);
			radiance = radiance.plus(incomingLight);

			return radiance;

		} else {

			return AMBIENT;

		}
		
	}
	
	/* Draw image. */
	public static void traceScene(Scene scene, Output output) {

		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < output.width; i++) {
			for(int j = 0; j < output.height; j++) {
				
				double px = ((double)i - (double)output.width / 2) / (double)output.width;
				double py = ((double)j - (double)output.height / 2) / (double)output.height;
				double xsz = 1 / output.width;
				double ysz = 1 / output.height;
				
				/* Do primary rays */
				TraceColor radiance = new TraceColor(0.0, 0.0, 0.0);
				for(int k = 0; k < NUM_PRIMARY_RAYS; k++) {
					Ray ray = new Ray(
						new Vector(0, 0, 0),
						new Vector(
							px + Math.random() * xsz - (xsz / 2),
							py + Math.random() * ysz - (ysz / 2),
							0.5
						)
					);
					radiance = radiance.plus(Tracer.traceRay(ray, scene.spheres, 0, -1));
				}
				radiance.div(NUM_PRIMARY_RAYS);

				/* Write pixel */
				output.writePixel(i, j, (int)radiance.red, (int)radiance.green, (int)radiance.blue);
	
			}
			
			/* Print progress */
			System.out.println(Math.floor(i * 100.0 / output.width) + "% (" + i + "/" + output.height + ")");
		}
		
		long finishTime = System.currentTimeMillis();
		long elapsed = finishTime - startTime;
		System.out.println("Took " + ((double)elapsed / 1000.0) + " seconds to render.");
		
		output.writeToFile("output.png");
		
	}
	
}


