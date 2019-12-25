package com.pathtracer2;

public class Vector {
	
	public double x, y, z;
	
	public Vector() {
		
	}
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector add(Vector vector1, Vector vector2) {
		return new Vector(vector1.x + vector2.x, vector1.y + vector2.y, vector1.z + vector2.z);
	}

	public static Vector sub(Vector vector1, Vector vector2) {
		return new Vector(vector1.x - vector2.x, vector1.y - vector2.y, vector1.z - vector2.z);
	}
	
	public static Vector multiply(Vector vector, double scal) {
		return new Vector(vector.x * scal, vector.y * scal, vector.z * scal);
	}
	
	public static Vector multiply(Vector vector1, Vector vector2) {
		return new Vector(vector1.x * vector2.x, vector1.y * vector2.y, vector1.z * vector2.z);
	}
	
	public double length() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
	}
	
	public Vector normalize() {
		return Vector.multiply(this, 1 / this.length());
	}
	
	public static Vector cross(Vector vector1, Vector vector2) {
		return new Vector(
			vector1.y * vector2.z - vector1.z * vector2.y,
			vector1.z * vector2.x - vector1.x * vector2.z,
			vector1.x * vector2.y - vector1.y * vector2.x
		);
	}
	
	public static double dot(Vector vector1, Vector vector2) {
		return vector1.x * vector2.x + vector1.y * vector2.y  + vector1.z * vector2.z;
	}
	
	public static Vector getOrthagonal(Vector vector) {
		boolean b0 = (vector.x < vector.y) && (vector.x < vector.z);
		boolean b1 = (vector.y <= vector.x) && (vector.y < vector.z);
		boolean b2 = (vector.z <= vector.x) && (vector.z <= vector.y);
		
		return Vector.cross(vector, new Vector(b0 ? 1 : 0, b1 ? 1 : 0, b2 ? 1 : 0));
	}
	
	public String toString() {
		return new String("(" + this.x + ", " + this.y + ", " + this.z + ")");
		//return new String("{\"x\":\"" + this.x + "\",\"y\":\"" + this.y + "\",\"z\":\"" + this.z + "\"} ");
	}
	
}

