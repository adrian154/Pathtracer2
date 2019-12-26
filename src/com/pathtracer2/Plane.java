package com.pathtracer2;

public class Plane extends WorldObject {
	
	public Vector normal;
	public Vector point;
	public Material material;
	
	public Plane() {
		
	}
	
	public Plane(Vector point, Vector normal, Material material) {
		this.normal = normal;
		this.point = point;
		this.material = material;
	}
	
}
