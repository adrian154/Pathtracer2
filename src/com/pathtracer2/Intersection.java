package com.pathtracer2;

public class Intersection {
	
	public Sphere sphere;
	public Vector point;
	public double distance;
	
	public Intersection(Sphere sphere, Vector point, double distance) {
		this.sphere = sphere;
		this.point = point;
		this.distance = distance;
	}
	
}
