package com.pathtracer2;

public class Tracer {
	
	/* Generate random vector in hemisphere */
	public static Vector randomInHemisphere() {
		double theta = 2 * Math.PI  * Math.random();
		double phi = Math.acos(2 * Math.random() - 1);
		double x = Math.sin(phi) * Math.cos(theta);
		double y = Math.sin(phi) * Math.sin(theta);
		double z = Math.abs(Math.cos(phi));
		return new Vector(x, y, z);
	}
	
	/* Generate random vector in hemisphere. */
	public static Vector randomInHemisphere(Vector normal) {
		
		/* Normalize input vector to be safe */
		normal = normal.normalize();
		
		/* First, generate random vector */
		Vector randomVector = randomInHemisphere();
		
		/* We need to translate this vector from the local coordinate space (where Z=(0,0,1)) to a global coordinate space where Z=Normal */
		/* U,V,W = basis vectors of local coord space (V=Normal) */
		Vector W = normal;
		Vector V = Vector.getOrthagonal(W);
		Vector U = Vector.cross(W, V);

		//System.out.println(W.toString() + ", " + V.toString() + ", " + U.toString());
		
		/* Convert randomVector to global cartesian coords */
		Vector result = new Vector(
			randomVector.x * U.x + randomVector.x * V.x + randomVector.x * W.x,
			randomVector.y * U.y + randomVector.y * V.y + randomVector.y * W.y,
			randomVector.z * U.z + randomVector.z * V.z + randomVector.z * W.z
		);
		
		return result;
	}
	
}
