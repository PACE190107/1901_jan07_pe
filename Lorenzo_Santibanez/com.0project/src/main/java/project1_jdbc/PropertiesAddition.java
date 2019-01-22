package project1_jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesAddition {

	public static void main(String[] args) {
//		try {
//			Properties properties = new Properties();
//			properties.setProperty("url", "jdbc:oracle:thin:@lorenzo-santibanez.clo5dbt0ejfd.us-east-2.rds.amazonaws.com:1521:RDS1901");
//			properties.setProperty("connectionUser", "lsantibanez7");
//			properties.setProperty("connectionPass", "Xbox494320");
//			properties.setProperty("SuperUsername","superuser");
//			properties.setProperty("superPass", "1234super");
// 
//			File file = new File("make");
//			FileOutputStream fileOut = new FileOutputStream(file);
//			properties.store(fileOut, "Data");
//
//			fileOut.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("url", "jdbc:oracle:thin:@orcl.clo5dbt0ejfd.us-east-2.rds.amazonaws.com:1521:RDS1901");
			prop.setProperty("connectionUser", "lsantibanez7");
			prop.setProperty("connectionPass", "password");
			prop.setProperty("superUsername","superuser");
			prop.setProperty("superPass", "1234super");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	}


