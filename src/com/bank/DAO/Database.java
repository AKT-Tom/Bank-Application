package com.bank.DAO;

import java.sql.*;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

//This is where we store the information to connect to our database.

public class Database {

    private static String url;
    private static String name;
    private static String DatabasePassword;

    static {
        try {
            InputStream input = Database.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");
            name = prop.getProperty("db.name");
            DatabasePassword = prop.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, DatabasePassword);
    }
}