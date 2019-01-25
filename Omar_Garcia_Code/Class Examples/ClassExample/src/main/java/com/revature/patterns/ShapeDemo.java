package com.revature.patterns;

public class ShapeDemo {
	
	public static void main(String[] args) {
		new ShapeFactory().getShape("Circle").draw();
		new ShapeFactory().getShape("Rectangle").draw();
		new ShapeFactory().getShape("Square").draw();
	}
}
