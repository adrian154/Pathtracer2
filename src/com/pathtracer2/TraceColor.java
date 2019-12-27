package com.pathtracer2;

public class TraceColor {
	
	public double red;
	public double green;
	public double blue;
	
	public TraceColor() {
		
	}
	
	public TraceColor(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public TraceColor times(TraceColor other) {
		return new TraceColor(
			red * other.red,
			green * other.green,
			blue * other.blue
		);
	}
	
	public TraceColor times(double scalar) {
		return new TraceColor(
			red * scalar,
			green * scalar,
			blue * scalar
		);
	}
	
	public TraceColor div(double scalar) {
		return new TraceColor(
			red / scalar,
			green / scalar,
			blue / scalar
		);
	}
	
	public TraceColor plus(TraceColor other) {
		return new TraceColor(
			red + other.red,
			green + other.green,
			blue + other.blue
		);
	}
	
	public TraceColor correct(double gamma) {
		return new TraceColor(
			Math.pow(red, gamma),
			Math.pow(green, gamma),
			Math.pow(green, gamma)
		);
	}
	
	public String toString() {
		return new String("(" + red + ", " + green + ", " + blue + ")");
	}
	
}
