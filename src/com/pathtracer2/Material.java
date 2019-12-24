package com.pathtracer2;

public class Material {
	
	public TraceColor emission;
	public TraceColor reflection;
	public double probReflective;
	public double probRefractive;
	
	public Material(TraceColor emission, TraceColor reflection, double probReflective, double probRefractive) {
		this.emission = emission;
		this.reflection = reflection;
		this.probReflective = probReflective;
		this.probRefractive = probRefractive;
	}
	
}
