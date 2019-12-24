package com.pathtracer2;


public class Sphere {
	
	public Vector center;
	public double radius;
	public Material material;
	
	public Sphere(Vector center, double radius, Material material) {
		this.center = center;
		this.radius = radius;
		this.material = material;
	}
	
}
