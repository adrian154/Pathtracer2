package com.pathtracer2;

import java.util.ArrayList;

public class IntersectionTester {
	
	public static double intersect(Ray ray, Sphere sphere) {
		Vector center = Vector.sub(sphere.center, ray.origin);
		Vector direction = ray.direction.normalize();
		
		double a = direction.x * direction.x + direction.y * direction.y + direction.z * direction.z;
		double b = -2 * (direction.x * center.x + direction.y * center.y + direction.z * center.z);
		double c = center.x * center.x + center.y * center.y + center.z * center.z - sphere.radius * sphere.radius;
		double disc = b * b - 4 * a * c;
		
		if(disc < 0) {
			/* No intersection. */
			return Double.POSITIVE_INFINITY;
		}
		
		/* Solve quadratic. */
		double t1 = -b + Math.sqrt(b * b - 4 * a * c);
		double t2 = -b - Math.sqrt(b * b - 4 * a * c);
		
		/* Return nearest position in front of camera. */
		if(t1 < 0 && t2 > 0) {
			return t2;
		} else if(t2 < 0 && t1 > 0) {
			return t1;
		} else if(t1 < 0 && t2 < 0) {
			return Double.POSITIVE_INFINITY;
		} else {
			return t1 < t2 ? t1 : t2;
		}
		
	}
	
	public static Intersection getIntersection(Ray ray, ArrayList<Sphere> spheres, int originIndex) {
		double nearestDistance = Double.POSITIVE_INFINITY;
		int index = 0;
		
		for(int i = 0; i < spheres.size(); i++) {
			double distance = IntersectionTester.intersect(ray, spheres.get(i));

			/* Check if it is nearest. */
			if(distance < nearestDistance && i != originIndex) {
				nearestDistance = distance;
				index = i;
			}
			
		}
		
		return new Intersection(spheres.get(index), ray.point(nearestDistance), nearestDistance, index);
	}
	
}
