package com.pathtracer2;

public class Material {
	
	public TraceColor emission;
	public TraceColor reflection;
	
	public Material(TraceColor emission, TraceColor reflection) {
		this.emission = emission;
		this.reflection = reflection;
	}
	
}
