package com.revature.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;


public class CollectionExample {

	final static Logger log = Logger.getLogger(CollectionExample.class); 
	public static void main(String[] args) {
		arrayListExample();
		setExample();
	}
	
	private static void arrayListExample() {
		List<String> superHeros = new ArrayList<String>();
		superHeros.add("SpiderMan");
		superHeros.add("BatMan");
		superHeros.add("SuperMan");
		superHeros.add("WonderWoman");
		superHeros.add("Hulk");
		superHeros.add("Thor");
		
		System.out.println("size: " + superHeros.size());
		System.out.println("get value2: " + superHeros.get(2));
		System.out.println("get value3: " + superHeros.get(3));
		System.out.println("remove: " + superHeros.remove(3));
		superHeros.add("IronMan");
		superHeros.add("Dr.Strange");
		System.out.println("size: " + superHeros.size());
		System.out.println("=======easiest way to traverse =======");
		log.info("size: " + superHeros.size());
		for(String s: superHeros) {
			System.out.println(s);
		}
		
		System.out.println(superHeros.toString());
		log.info(superHeros.toString());
	}
	
	private static void setExample() {
		Set<String> moreHeros = new HashSet<String>();
		moreHeros.add("DareDevil");
		moreHeros.add("Wolverine");
		moreHeros.add("Flash");
		moreHeros.add("Black Panther");
		moreHeros.add("Aquaman");
		System.out.println(moreHeros);
	}
	
	
	

}
