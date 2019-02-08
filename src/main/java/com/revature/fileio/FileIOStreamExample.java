package com.revature.fileio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOStreamExample {

	static FileInputStream fis = null;
	static FileOutputStream fos = null;
	
	public static void main(String[] args)  {
		try {
			readWriteBytes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Something bad happened");
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static void readWriteBytes() throws IOException, FileNotFoundException {
		fis = new FileInputStream("src\\main\\resources\\input.txt");
		fos = new FileOutputStream("src\\main\\resources\\output.txt");
		int x;
		//use different read method for filereader - use readline and null instead of read and -1
		while ((x = fis.read()) != (-1)) {
			fos.write(x);
		}
	}
}
