package com.pathtracer2;

import java.util.ArrayList;

public class IntersectionTester {
	
	public static double intersect(Ray ray, WorldObject object) {
		if(object instanceof Sphere) {
			return intersect(ray, (Sphere)object);
		} else if(object instanceof Plane) {
			return intersect(ray, (Plane)object);
		} else {
			
			/* To keep Eclipse from complaining */
			return 0.0;
			
		}
	}
	
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
			return Double.POSITIVE_INFINITY;
		}
		
		/* Solve for distance */
		double distance = Vector.dot(Vector.sub(plane.point, ray.origin), plane.normal) / denom;
		if(distance < 0) {
			return Double.POSITIVE_INFINITY;
		} else {
			return distance;
		}
		
	}
	
	public static Intersection getIntersection(Ray ray, ArrayList<WorldObject> objects, int originIndex) {
		
		double nearestDistance = Double.POSITIVE_INFINITY;
		int index = 0;
		
		for(int i = 0; i < objects.size(); i++) {
			WorldObject object = objects.get(i);
			double distance = IntersectionTester.intersect(ray, object);
			
			if(distance < nearestDistance && i != originIndex) {
				
			}
				
		}
		
		/*
		double nearestDistanceSph = Double.POSITIVE_INFINITY;
		int indexSph = 0;
		
		for(int i = 0; i < spheres.size(); i++) {
			double distance = IntersectionTester.intersect(ray, spheres.get(i));

			if(distance < nearestDistanceSph && i != originIndex) {
				nearestDistanceSph = distance;
				indexSph = i;
			}
			
		}
		
		double nearestDistancePln = Double.POSITIVE_INFINITY;
		int indexPln = 0;
		
		for(int i = 0; i < planes.size(); i++) {
			double distance = IntersectionTester.intersect(ray, planes.get(i));
		
			if(distance < nearestDistancePln && i != originIndex + spheres.size()) {
				nearestDistancePln = distance;
				indexPln = i;
			}
		}
		
		if(nearestDistancePln < nearestDistanceSph) {
			Vector point = ray.point(nearestDistancePln);
			Vector normal = planes.get(indexPln).normal;
			Material material = planes.get(indexPln).material;
			
			return new Intersection(material, point, normal, nearestDistancePln, indexPln + spheres.size());
		
		} else if(nearestDistanceSph < nearestDistancePln) {
			Vector point = ray.point(nearestDistanceSph);
			Vector normal = Vector.sub(point, spheres.get(indexSph).center).normalize();
			Material material = spheres.get(indexSph).material;
			
			return new Intersection(material, point, normal, nearestDistanceSph, indexSph);
			
		} else {
		
			return new Intersection(new Material(), new Vector(), new Vector(), Double.POSITIVE_INFINITY, 0);
			
		}
		*/
		
		/*
		double floorDistance = (Tracer.FLOOR - ray.origin.y) / ray.direction.y;
		
		if(Tracer.HAS_FLOOR && floorDistance > 0 && floorDistance < nearestDistance && originIndex != -2) {
			Vector point = ray.point(floorDistance);
			Vector normal = new Vector(0.0, 1.0, 0.0);
			
			boolean grid = Math.sin(point.x * 3) > 0 ^ Math.sin(point.z * 3) > 0;
			Material material = new Material(new TraceColor(0.0, 0.0, 0.0), grid ? new TraceColor(1.0, 1.0, 1.0) : new TraceColor(0.0, 0.0, 0.0), 0.0);
			
			return new Intersection(material, point, normal, floorDistance, -2);
		} else {
			Sphere sphere = spheres.get(index);
			Vector point = ray.point(nearestDistance);
			Vector normal = Vector.sub(point, sphere.center).normalize();
			return new Intersection(sphere.material, point, normal, nearestDistance, index);
		}
		*/
		
	}
	
}
