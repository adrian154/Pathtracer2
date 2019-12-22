package com.pathtracer2;

import java.util.ArrayList;

public class Tracer {
	
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
		//Vector result = Vector.add(Vector.multiply(W, randomVector.z), Vector.add(Vector.multiply(U, randomVector.x), Vector.multiply(V, randomVector.y)));
		Vector result = new Vector(
			randomVector.x * U.x + randomVector.y * V.x + randomVector.z * W.x,
			randomVector.x * U.y + randomVector.y * V.y + randomVector.z * W.y,
			randomVector.x * U.z + randomVector.y * V.z + randomVector.z * W.z
		);
		
		return result;
	}
	
	/* Trace primary ray. */
	public static double traceRay(Ray ray, ArrayList<Sphere> spheres, int bounces, int originIndex) {
		
		if(bounces > 3) {
			return 0;
		}
		
		Intersection intersection = IntersectionTester.getIntersection(ray, spheres, originIndex);
	
		if(intersection.distance < Double.POSITIVE_INFINITY) {
		
			double radiance = intersection.sphere.emission;
			
			/* Do some samples. */
			double incomingLight = 0;

			for(int i = 0; i < Main.NUM_SECONDARY_RAYS; i++) {
				Vector normal = Vector.sub(intersection.point, intersection.sphere.center).normalize();
				Vector newDirection = randomInHemisphere(normal).normalize();
				
				Ray newRay = new Ray(intersection.point, newDirection);
				incomingLight += traceRay(newRay, spheres, bounces + 1, intersection.sphereIndex) * Vector.dot(newDirection, normal);
			}
			
			incomingLight /= Main.NUM_SECONDARY_RAYS;
			radiance += incomingLight;
			
			return radiance;

		} else {

			return 0.0;

		}
		
	
	}
}



