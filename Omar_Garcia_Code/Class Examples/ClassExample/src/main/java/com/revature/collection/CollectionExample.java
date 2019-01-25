package com.revature.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.logging.LoggingExample;

public class CollectionExample {
	final static Logger log = Logger.getLogger(LoggingExample.class);

	public static void main(String[] args) {
		arrayListExample();
		//setExample();
	}
	
	public static void arrayListExample() {
		List<String> superhero = new ArrayList<String>();
		superhero.add("Spider-Man");
		superhero.add("Batman");
		superhero.add("Superman");
		superhero.add("WonderWoman");
		superhero.add("Hulk");
		superhero.add("Thor");
		
		log.info("Size : " + superhero.size());
		log.info("get value 2 : " + superhero.get(2));
		log.info("get value 3 : " + superhero.get(3));
		log.info("remove value 3 : " +superhero.remove(3));
		superhero.add("Iron-Man");
		superhero.add("Dr. Strange");
		log.info("size : " + superhero.size());
		
		for(String s : superhero) {
			log.info(s);
		}
		
		log.info(superhero.toString());
	}
	
	private static void setExample() {
		Set<String> moreHeros = new HashSet<String>();
		moreHeros.add("DareDevile");
		moreHeros.add("IronFist");
		moreHeros.add("Wolverine");
		moreHeros.add("Flash");
		moreHeros.add("BlackPather");
		moreHeros.add("Aquaman");
		log.info(moreHeros);
	}
}
