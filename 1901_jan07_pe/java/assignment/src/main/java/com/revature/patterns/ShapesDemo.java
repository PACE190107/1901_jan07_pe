package com.revature.patterns;

public class ShapesDemo {

	public static void main(String[] args) {
		new ShapeFactory().getShape("circle").draw();
		new ShapeFactory().getShape("rectangle").draw();
		new ShapeFactory().getShape("square").draw();
	}

}
