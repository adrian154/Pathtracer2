package com.pathtracer2;

public class Material {
	
	public TraceColor emission;
	public TraceColor reflection;
	public double probReflective;
	
	public Material() {
		
	}
	
	public Material(TraceColor emission, TraceColor reflection, double probReflective) {
		this.emission = emission;
		this.reflection = reflection;
		this.probReflective = probReflective;
	}
	
}
