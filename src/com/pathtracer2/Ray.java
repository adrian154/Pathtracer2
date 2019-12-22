package com.pathtracer2;

public class Ray {
	
	public Vector direction;
	public Vector origin;
	
	public Ray(Vector origin, Vector direction) {
		this.direction = direction.normalize();
		this.origin = origin;
	}
	
	/* Get point on ray given distance from origin */
	public Vector point(double distance) {
		return Vector.add(this.origin, Vector.multiply(this.direction, distance));
	}
	
}
