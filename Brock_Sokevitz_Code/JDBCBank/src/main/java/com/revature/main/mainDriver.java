package com.revature.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.LoginOrRegisterException;
import com.revature.exceptions.TooManyLoginAttemptsException;
import com.revature.exceptions.UserDoesntExistException;
import com.revature.exceptions.UserExistsException;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.JDBCConnectUtil;

public class mainDriver {

	public static void main(String[] args) {
		setupDefaultConnection();
		loginOrRegister();
		
	}
	
	public static boolean setupDefaultConnection() {
		boolean connectionSetup = true;
		try {
			File file = new File("superuser.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			
			properties.load(fileInput);
			fileInput.close();
			Map<String,String> superuserMap = new HashMap<String, String>();
			@SuppressWarnings("rawtypes")
			Enumeration keys = properties.keys();
			
			while(keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = properties.getProperty(key);
				superuserMap.put(key, value);
			}
			System.out.println("connection setup");
			JDBCConnectUtil.setConnection(superuserMap.get("url"), superuserMap.get("username"), superuserMap.get("password"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			connectionSetup = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connectionSetup;
	}

	
	static void loginOrRegister() {
		System.out.println("Would you like to register, login, or exit.");
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		while(!input.equals("exit")) {
			
			try {
				
				if(input.equalsIgnoreCase("login")) {
					
					verifyLoginInformation();
					System.out.println("Would you like to register, login, or exit?");
					input = sc.nextLine();
					
				}else if(input.equalsIgnoreCase("register")) {
					register();
					System.out.println("Would you like to register, login, or exit?");
					input = sc.nextLine();
				}else if(input.equalsIgnoreCase("exit")){
					
				}else{
					throw new LoginOrRegisterException();
				} 
				
			}catch(LoginOrRegisterException e){
				System.out.println(e.getMessage());
				input = sc.nextLine();
			}
			
		}
	}
	
	static void register() {		
			String username = establishUsername();
			if(!username.equalsIgnoreCase("exit")) {
				String password = establishPassword();
			
				UserService.getUserService().createUserConnection(new User(username, password, 0));
				UserService.getUserService().registerUser(new User(username, password, 0));
				System.out.println("User has been registered.");
			}
	}
	
	static String establishUsername() {
		boolean usernameEstablished = false;
		
		System.out.println("Please register a username.");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
		
		while(!usernameEstablished && !username.equalsIgnoreCase("exit")) {
			try {		
				if(username.equals("") || username.equalsIgnoreCase("exit")) {
					throw new InvalidInputException();
				}else if(UserService.getUserService().checkUsername(username)) {
					throw new UserExistsException();
				}else {
					usernameEstablished = true;
				}
				
			}catch(InvalidInputException e) {
				System.out.println(e.getMessage() + " Please enter a valid username or type exit.");
				username = sc.nextLine();
			}catch(UserExistsException u) {
				System.out.println(u.getMessage() + " Please enter a valid username or type exit.");
				username = sc.nextLine();
			}
		}
		return username;
	}
	
	static String establishPassword() {
		boolean passwordEstablished = false;
		
		System.out.println("Please enter a password.");
		char password[] = System.console().readPassword();
		String strPassword = new String(password);
		
		while(!passwordEstablished) {
				try {
					if(strPassword.equals("")||strPassword.equalsIgnoreCase("exit")) {
						throw new InvalidInputException();
					}else {
						passwordEstablished = true;
					}
				}catch(InvalidInputException e) {
					System.out.println(e.getMessage() + " Please enter a valid password.");
					password = System.console().readPassword();
					strPassword = new String(password);					
				}
				
		}
		return strPassword;
	}
	
	static void verifyLoginInformation() {
		
		String username = verifyUserExists();
		if(!username.equalsIgnoreCase("exit")) {
		boolean correctPassword = verifyPassword(username);
		if(correctPassword) {
			
			User user = UserService.getUserService().getUserDetails(username);	
			
			if(user.getSuperuser()>0) {
				System.out.println("Superuser logged in.");
				SuperuserLoggedIn.login(user);
				System.out.println("Superuser logged out.");
			}else {
				System.out.println("User logged in.");
				UserLoggedIn.login(user);
				System.out.println("User logged out.");
			}
			setupDefaultConnection();
		}		
		}
		
	}

	static String verifyUserExists() {
		boolean userExists = false;
		
		System.out.println("Please enter a username.");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
		
		while(!userExists && !username.equalsIgnoreCase("exit")) {
			try {
				if(!UserService.getUserService().checkUsername(username)) {
					throw new UserDoesntExistException();
				}else {
					
					return username;
				}
					
			}catch(UserDoesntExistException u) {
				System.out.println(u.getMessage());
				username = sc.nextLine();
			}
		}
		return username;		
	}
	
	static boolean verifyPassword(String username) {
		int passwordAttempts = 0;
		
		System.out.println("Please enter a password.");
		char password[] = System.console().readPassword();
		String strPassword = new String(password);
		
		while(passwordAttempts<3||strPassword.equalsIgnoreCase("exit")) {
			try {
				if(passwordAttempts == 3) {
					throw new TooManyLoginAttemptsException();
				}else if(!UserService.getUserService().checkPassword(username, strPassword)) {
					passwordAttempts++;
					throw new IncorrectPasswordException();
				}else {
					JDBCConnectUtil.setConnection(username, strPassword);
					return true;
					}
			}catch(TooManyLoginAttemptsException la) {
				System.out.println(la.getMessage());
			}catch(IncorrectPasswordException ipe) {
				System.out.println(ipe.getMessage());
				password = System.console().readPassword();
				strPassword = new String(password);
			}
		}
		return false;
		
	}
	}
