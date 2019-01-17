package com.revature.patterns;

public class ShapeFactory {
	public Shape getShape(String s) {
		if (s.equalsIgnoreCase("rectangle")) {
			return new Rectangle();
		} else if (s.equalsIgnoreCase("circle")) {
			return new Circle();
		} else if (s.equalsIgnoreCase("square")) {
			return new Square();
		} else if (s.equals(null)) {
			return null;
		} else
		return null;
	}
}
