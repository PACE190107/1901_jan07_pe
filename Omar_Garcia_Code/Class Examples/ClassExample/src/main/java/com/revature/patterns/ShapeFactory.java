package com.revature.patterns;

public class ShapeFactory {
	
	public Shape getShape(String s){
		if(s.equals("Circle")) {
			return new Circle();
		}
		else if (s.equals("Rectangle")) {
			return new Rectangle();
		}
		else if (s.equals("Square")) {
			return new Square();
		}
		
		return null;
	}
}
