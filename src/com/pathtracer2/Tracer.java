package com.pathtracer2;

import java.util.ArrayList;

public class Tracer {
	
	/* Generate random vector in hemisphere */
	public static Vector randomInHemisphere() {
		double theta = 2 * Math.PI  * Math.random();
		double phi = Math.acos(2 * Math.random() - 1);
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
		Vector Vy = normal;
		Vector Vz = Vector.getOrthagonal(Vy);
		Vector Vx = Vector.cross(Vz, Vy);
		
		/* Convert randomVector to global cartesian coords */
		Vector result = new Vector(
			randomVector.x * Vx.x + randomVector.y * Vy.x + randomVector.z * Vz.x,
			randomVector.x * Vx.y + randomVector.y * Vy.y + randomVector.z * Vz.y,
			randomVector.x * Vx.z + randomVector.y * Vy.z + randomVector.z * Vz.z
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
			for(int i = 0; i < Main.numSecRays; i++) {
				Vector diff = Vector.sub(intersection.point, intersection.sphere.center);
				Vector normal = diff.normalize();
				
				Vector newDirection = randomInHemisphere(normal).normalize();
				Ray newRay = new Ray(Vector.add(Vector.multiply(diff, 1.001), intersection.sphere.center), newDirection);
				incomingLight += traceRay(newRay, spheres, bounces + 1);// * Vector.dot(newDirection, normal);
			}
			
			incomingLight /= Main.numSecRays;
			radiance += incomingLight;
			
			return radiance;
		} else {
			return 0.0;
		}
		
	
	}
}
