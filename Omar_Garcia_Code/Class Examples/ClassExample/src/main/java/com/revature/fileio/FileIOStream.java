package com.revature.fileio;

import java.io.*;

public class FileIOStream {

	static FileInputStream fis = null;
	static FileOutputStream fos = null;
	
	public static void main(String[] args) {
		try {
			readWriteBytes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Something bad happened");
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static void readWriteBytes() throws IOException{
		
		fis = new FileInputStream("src\\main\\resources\\input.txt");
		int c;
		fos = new FileOutputStream("src\\main\\resources\\output.txt");
		while((c = fis.read()) != -1) {
			//System.out.print((char) c);
			fos.write(c);;
			
		}
	}
	
	static void writeBytes() {
		
	}
}
