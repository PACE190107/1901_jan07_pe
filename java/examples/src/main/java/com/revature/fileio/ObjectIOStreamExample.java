package com.revature.fileio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class ObjectIOStreamExample {

	ObjectInputStream ois;
	ObjectOutputStream oos;

	void performDeSerialization(Person p, String s) throws FileNotFoundException, IOException, ClassNotFoundException {
		ois = new ObjectInputStream(new FileInputStream(s));
		Person Mike = (Person) ois.readObject();
		System.out.println(Mike.getDateOfBirth() +
				Mike.getName() + Mike.getOccupation() + 
				Mike.getSsn());
		ois.close();
	}
	
	void performSerialization(Object o, String s) throws FileNotFoundException, IOException, ClassNotFoundException {
		oos = new ObjectOutputStream(new FileOutputStream(s));
		oos.writeObject(o);
		oos.close();
	}
	public static void main(String[] args) {
		try {
			new ObjectIOStreamExample().performSerialization(new Person(), "src\\main\\resources\\serialization.txt");
			new ObjectIOStreamExample().performDeSerialization(new Person(), "src\\main\\resources\\serialization.txt");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
