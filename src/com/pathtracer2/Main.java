package com.pathtracer2;

public class Main {
	public static final int WIDTH = 256;
	public static final int HEIGHT = 256;
	
	public static void main(String[] args) {
		Output output = new Output(WIDTH, HEIGHT);
		
		for(int i = 0; i < 100; i++) {
			System.out.println(Tracer.randomInHemisphere(new Vector(0, -1, 0)).toString());
		}
		
		output.writeToFile("output.png");
	}
	
}
