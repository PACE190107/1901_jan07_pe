package com.revature.lambda;

public class Wizard {
	
	String name;
	Magic proficiency;
	
	public Wizard(String name, Magic proficiency) {
		super();
		this.name = name;
		this.proficiency = proficiency;
	}
	public static void main(String[] args) {
		Magic fireball = () -> {System.out.println("fireball");};
		//fireball.castSpell();
		
		Wizard fireWizard = new Wizard("Gandalf", fireball);
		fireWizard.proficiency.castSpell();
		
		Wizard iceWizard = new Wizard("Dumbledore", () -> System.out.println("ice beam"));
		iceWizard.proficiency.castSpell();
	}
}
