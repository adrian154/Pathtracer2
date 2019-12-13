package com.pathtracer2;


public class Sphere {
	
	public Vector center;
	public double radius;
	public double emission;
	
	public Sphere(Vector center, double radius, double emission) {
		this.center = center;
		this.radius = radius;
		this.emission = emission;
	}
	
}
