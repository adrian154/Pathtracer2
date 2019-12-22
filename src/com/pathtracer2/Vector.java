package com.pathtracer2;

public class Vector {
	
	public double x, y, z;
	
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

/* 
VIS HACK

function addVec(vec, num) {
    demo.totalVectorsCount++; demo.vVectorsCount++; demo.vecList.push("v" + (num + 6)); demo["v" + (num + 6)] = [vec.x,vec.y,vec.z];
}
function plt(str) {
    let arr = str.split(" ");
    for(let i = 0; i < arr.length; i++) {
        console.log(arr[i]);
        addVec(JSON.parse(arr[i], i));
    }
    demo.threeDPlot();
}

*/

//{x:"0.5431312424126337",y:"0.735740852511143",z:"-0.40459096809183154"} {x:"0.5457170559806535",y:"-0.08094073579134502",z:"0.8340512526825697"} {x:"0.31813665062177127",y:"0.8296710554801762",z:"-0.4587319601134977"} {x:"0.3473270870298816",y:"-0.7649474830774372",z:"-0.5424198030574021"} {x:"0.1307844750289731",y:"-0.5198363848737098",z:"0.8441952108682139"} {x:"0.9749674439840841",y:"-0.20290654960643678",z:"-0.09092532814322128"} {x:"0.9855298486458626",y:"0.04551713532110908",z:"0.1632761703991828"} {x:"0.48158984015247014",y:"0.806676407785006",z:"0.3425556874217636"} 