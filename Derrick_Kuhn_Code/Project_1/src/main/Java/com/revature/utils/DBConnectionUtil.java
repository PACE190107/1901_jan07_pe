package com.revature.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtil {

    private static String driver;
    private static String url;
    private static String user;
    private static String pass;
    private static DBConnectionUtil instance = new DBConnectionUtil();

    private DBConnectionUtil(){}

    public static DBConnectionUtil getInstance(){
        return instance;
    }

    static {
        dbConnectionInfo();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        //hard coding credentials is not a good practice
        //how do you establish connection using jdbc?
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static void dbConnectionInfo() {
        try {
            Properties prop = new Properties();
            prop.load(new FileReader("C:\\derrick_kuhn_repos\\project1.properties"));
            driver = prop.getProperty("driver");
            url = prop.getProperty("dbs");
            user = prop.getProperty("username");
            pass = prop.getProperty("password");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("File not found for DBConnection");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
