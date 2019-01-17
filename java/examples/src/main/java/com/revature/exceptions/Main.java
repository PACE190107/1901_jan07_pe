package com.revature.exceptions;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args)  {
		try {
			new Main().riskyBehavior();
		} 
		catch (HankException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void riskyBehavior() throws HankException, SQLException{
		System.out.println("before exception");
		//throw new HankException();
		throw new SQLException();
		//line of code will not get executed
		//System.out.println("after exception");
	}
}
