package com.pathtracer2;

public class Intersection {
	
	public Material material;
	public int index;
	public Vector point;
	public Vector normal;
	public double distance;
	
	public Intersection() {
		
	}
	
	public Intersection(Material material, Vector point, Vector normal, double distance, int index) {
		this.material = material;
		this.point = point;
		this.normal = normal;
		this.distance = distance;
		this.index = index;
	}
	
}
