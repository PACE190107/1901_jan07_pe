package com.revature.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRWBuffereRWExample {
	
	static BufferedReader bf = null;
	static BufferedWriter bw = null;
	
	public static void main(String[] args) {
		try {
			readWriteBytes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Something bad happened");
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static void readWriteBytes() throws IOException{
		
		bf = new BufferedReader(new FileReader("src\\main\\resources\\input.txt"));
		String c;
		bw = new BufferedWriter(new FileWriter("src\\main\\resources\\output.txt"));
		while((c = bf.readLine()) != null) {
			System.out.print(c);
			bw.write(c);;
			
		}
	}
	

	static void writeBytes() {
		
	}
}
