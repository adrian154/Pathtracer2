package com.pathtracer2;

public class Transform {
	public static Vector transform(Vector point, double pitch, double roll, double yaw) {
		double cosa = Math.cos(yaw);
		double sina = Math.sin(yaw);
		
		double cosb = Math.cos(pitch);
		double sinb = Math.sin(pitch);
		
		double cosc = Math.cos(roll);
		double sinc = Math.sin(roll);
		
		double Axx = cosa * cosb;
		double Axy = cosa * sinb * sinc - sina * cosc;
		double Axz = cosa * sinb * cosc + sina * sinc;
		
		double Ayx = sina * cosb;
		double Ayy = sina * sinb * sinc + cosa * cosc;
		double Ayz = sina * sinb * cosc - cosa * sinc;
		
		double Azx = -sinb;
		double Azy = cosb * sinc;
		double Azz = cosb * cosc;
		
		return new Vector(
			Axx * point.x + Axy * point.y + Axz * point.z,
			Ayx * point.x + Ayy * point.y + Ayz * point.z,
			Azx * point.x + Azy * point.y + Azz * point.z
		);
	}
}




