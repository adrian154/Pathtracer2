package com.pathtracer2;

import java.util.ArrayList;

public class Tracer {
	
	/* Generate random vector in hemisphere */
	public static Vector randomInHemisphere() {
		double theta = 2 * Math.PI  * Math.random();
		double phi = Math.acos(2 * Math.random() - 1);
		double x = Math.sin(phi) * Math.cos(theta);
		double y = Math.abs(Math.cos(phi));
		double z = Math.sin(phi) * Math.sin(theta);
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
		
		System.out.println("U: " + U.toString() + ", " + ", V:" + V.toString() + ", W:" + W.toString());
		
		/* Convert randomVector to global cartesian coords */
		Vector result = new Vector(
			randomVector.x * U.x + randomVector.x * V.x + randomVector.x * W.x,
			randomVector.y * U.y + randomVector.y * V.y + randomVector.y * W.y,
			randomVector.z * U.z + randomVector.z * V.z + randomVector.z * W.z
		);
		
		return result;
	}
	
	/* Trace primary ray. */
	public static double traceRay(Ray ray, ArrayList<Sphere> spheres, int bounces) {
		Intersection intersection = IntersectionTester.getIntersection(ray, spheres);
			
		if(bounces > 1) {
			return 0;
		}
		
		if(intersection.distance < Double.POSITIVE_INFINITY) {
			double radiance = intersection.sphere.emission;
			
			/* Do some samples. */
			double incomingLight = 0;
			for(int i = 0; i < 10; i++) {
				
			}
			
			return radiance;
		} else {
			return 1.0;
		}
		
	
	}
}
