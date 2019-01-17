package com.revature.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRWBufferedRWExample {

	static BufferedReader bf = null;
	static BufferedWriter bw = null;
	public static void main(String[] args)  {
		try {
			readWriteBytes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			System.out.println("Something bad happened");
		} 
		finally {
			try {
				bf.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	static void readWriteBytes() throws  IOException, FileNotFoundException{
		bf = new BufferedReader(new FileReader("src\\main\\resources\\input.txt"));
		bw = new BufferedWriter(new FileWriter("src\\main\\resources\\output_buffered.txt"));
		String x;
		while((x = bf.readLine()) != null){
			//System.out.println(bf.readLine());
			bw.write(x);
		}		
	}
}
