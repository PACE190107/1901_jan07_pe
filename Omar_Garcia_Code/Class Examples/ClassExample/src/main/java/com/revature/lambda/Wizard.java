package com.revature.lambda;

public class Wizard {
	
	String name;
	magic proficiency;
	
	public Wizard(String name, magic proficiency) {
		this.name = name;
		this.proficiency = proficiency;
		
	}
	
	public static void main(String[] args) {
	magic fireball = () -> System.out.println("Fireball");
	//fireball.castSpell();
	
	Wizard fireWizard = new Wizard("Gandalf", fireball);
	fireWizard.proficiency.castSpell();
	
	Wizard iceWizard = new Wizard("Dumbledore", () -> System.out.println("Ice Beam"));
	iceWizard.proficiency.castSpell();
	}
}
