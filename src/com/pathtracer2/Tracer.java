package com.pathtracer2;

import java.util.ArrayList;

public class Tracer {
	
	public static final int NUM_SECONDARY_RAYS = 4;
	public static final int NUM_PRIMARY_RAYS = 4;
	public static final int NUM_BOUNCES = 3;
	
	public static TraceColor AMBIENT = new TraceColor(0.0, 0.0, 0.0);
	
	public static double FOCAL_LENGTH = 1.0;
	public static double FOCAL_PLANE = 3.0;
	public static double APERTURE = 0.1;
	
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
	public static Vector getDiffuseVector(Vector normal) {
		
		/* Normalize input vector to be safe */
		normal = normal.normalize();
		
		/* First, generate random vector */
		Vector randomVector = randomInHemisphere();
		
		/* We need to translate this vector from the local coordinate space (where Z=(0,0,1)) to a global coordinate space where Z=Normal */
		/* U,V,W = basis vectors of local coord space (V=Normal) */
		Vector V = normal;
		Vector U = Vector.getOrthagonal(V);
		Vector W = Vector.cross(V, U);
		
		/* Convert randomVector to global cartesian coords */
		Vector result = new Vector(
			randomVector.x * U.x + randomVector.y * V.x + randomVector.z * W.x,
			randomVector.x * U.y + randomVector.y * V.y + randomVector.z * W.y,
			randomVector.x * U.z + randomVector.y * V.z + randomVector.z * W.z
		);
		
		return result;
	}

	/* Get reflection vector */
	public static Vector getReflectVector(Vector incident, Vector normal) {
		return Vector.sub(incident, Vector.multiply(normal, 2 * Vector.dot(incident, normal)));	
	}
	
	/* Get refraction vector */
	public static Vector getRefractVector(Vector incident, Vector normal) {
		return new Vector(0, 0, 0);
	}
	
	/* Trace primary ray. */
	public static TraceColor traceRay(Ray ray, ArrayList<WorldObject> objects, int bounces, int originIndex) {
		
		if(bounces > NUM_BOUNCES) {
			return new TraceColor(0.0, 0.0, 0.0);
		}
		
		Intersection intersection = IntersectionTester.getIntersection(ray, objects, originIndex);

		if(intersection.distance < Double.POSITIVE_INFINITY) {
			
			TraceColor radiance = intersection.material.emission;
			TraceColor incomingLight = new TraceColor(0.0, 0.0, 0.0);

			/* Sample secondary rays. */
			for(int i = 0; i < NUM_SECONDARY_RAYS; i++) {

				/* Get new sampling direction. */
				Vector newDirection;
				if(Math.random() < intersection.material.probReflective) {
					newDirection = getReflectVector(ray.direction, intersection.normal).normalize();
				} else {
					newDirection = getDiffuseVector(intersection.normal).normalize();
				}
	
				/* Do sample */
				Ray newRay = new Ray(intersection.point, newDirection);
				incomingLight = incomingLight.plus(traceRay(newRay, objects, bounces + 1, intersection.index).times(Vector.dot(newDirection, intersection.normal)));
			
			}

			
			/* Calculate incoming light */
			incomingLight = incomingLight.div(NUM_SECONDARY_RAYS);
			incomingLight = incomingLight.times(intersection.material.reflection);
			radiance = radiance.plus(incomingLight);

			/* Return radiance (for recursion) */
			return radiance;

		} else {

			/* Return ambient light */
			return AMBIENT;

		}
		
	}
	
	/* Draw image. */
	public static void traceScene(Scene scene, Output output) {
		
		TraceColor[][] pic = new TraceColor[output.width][output.height];
		
		for(int i = 0; i < output.width; i++) {
			for(int j = 0; j < output.height; j++) {
				pic[i][j] = new TraceColor(0.0, 0.0, 0.0);
			}
		}
		
		for(int pass = 0; pass < 100000; pass++) {
			
			for(int i = 0; i < output.width; i++) {
				
				for(int j = 0; j < output.height; j++) {
					
					/* Get ray */
					double px = ((double)i - (double)output.width / 2) / (double)output.width;
					double py = ((double)j - (double)output.height / 2) / (double)output.height;
					Ray ray = new Ray(new Vector(0, 0, 0), new Vector(px, py, FOCAL_LENGTH));
					
					/* Simulate aperture */
					double focalDist = (FOCAL_PLANE - ray.origin.z) / ray.direction.z;
					Vector focalPoint = ray.point(focalDist);
					
					double angle = Math.random() * Math.PI * 2;
					double distance = Math.random() * APERTURE;
					Vector newPoint = new Vector(Math.cos(angle) * distance, Math.sin(angle) * distance, FOCAL_LENGTH);
					Ray newRay = new Ray(newPoint, Vector.sub(focalPoint, newPoint).normalize());
								
					/* Do primary rays */
					TraceColor radiance = Tracer.traceRay(newRay, scene.objects, 0, -1);
					pic[i][j] = pic[i][j].plus(radiance);
						
				}
				
				/* Print progress */
				System.out.println(Math.floor(i * 100.0 / output.width) + "% (" + i + "/" + output.height + ")");
			
			}
			
			for(int i = 0; i < output.width; i++) {
				for(int j = 0; j < output.height; j++) {
					TraceColor radiance = pic[i][j].div(pass + 1);
					output.writePixel(i, output.height - 1 - j, (int)radiance.red, (int)radiance.green, (int)radiance.blue);
				}
			}
			
			output.writeToFile(pass + ".png");
			
		}

	}
	
}


