package com.revature.tjordan.base;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
		public static String getMd5(String input) {
			
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				
				byte[] messageDigest = md.digest(input.getBytes());
				
				BigInteger no = new BigInteger(1, messageDigest);
				
				
				String hashtext = no.toString(16);
				while(hashtext.length() < 32) {
					hashtext = "0" + hashtext;
				}
				
				return hashtext;
			}
			
			
			catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
				
			}
		}
}
