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
		double t1 = (-b + Math.sqrt(disc)) / (2 * a);
		double t2 = (-b - Math.sqrt(disc)) / (2 * a);
		
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
	
	public static double intersect(Ray ray, Plane plane) {
		
		/* Denominator of equation, determines if there is a solution */
		double denom = Vector.dot(ray.direction, plane.normal);
		if(denom == 0) {
	
			/* No intersection. */
			return Double.POSITIVE_INFINITY;
			
		}
		
		/* Solve for distance */
		double distance = -Vector.dot(Vector.sub(ray.origin, plane.point), plane.normal) / denom;
		if(distance < 0) {
			return Double.POSITIVE_INFINITY;
		} else {
			return distance;
		}
		
	}
	
	/* Test ray-triangle intersection */
	/*
	public static double intersect(Ray ray, Triangle triangle) {
		Vector normal = Vector.cross(triangle.vertexes[0], triangle.vertexes[1]).normalize();
		Vector point = triangle.vertexes[0];
	}
	*/
	
	public static Intersection getIntersection(Ray ray, ArrayList<WorldObject> objects, int originIndex) {
		
		double nearestDistance = Double.POSITIVE_INFINITY;
		int index = 0;
		
		for(int i = 0; i < objects.size(); i++) {
			WorldObject object = objects.get(i);
			double distance;
			
			if(object instanceof Sphere) {
				distance = intersect(ray, (Sphere)object);
			} else if(object instanceof Plane) {
				distance = intersect(ray, (Plane)object);
			} else {
				
				/* To appease Eclipse */
				distance = Double.POSITIVE_INFINITY;
				
			}
			
			if(distance < nearestDistance && i != originIndex) {
				nearestDistance = distance;
				index = i;
			}
				
		}
		
		WorldObject nearest = objects.get(index);
		if(nearest instanceof Plane) {
			Plane plane = (Plane)nearest;
			Vector point = ray.point(nearestDistance);
			Vector normal = plane.normal;
			Material material = plane.material;
			
			return new Intersection(material, point, normal, nearestDistance, index);
		
		} else if(nearest instanceof Sphere) {
			Sphere sphere = (Sphere)nearest;
			Vector point = ray.point(nearestDistance);
			Vector normal = Vector.sub(point, sphere.center).normalize();
			Material material = sphere.material;
			
			return new Intersection(material, point, normal, nearestDistance, index);
			
		} else {
		
			return new Intersection(new Material(), new Vector(), new Vector(), Double.POSITIVE_INFINITY, 0);
			
		}
		
	}
	
}
