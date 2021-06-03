package com.formation.ExampleDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL="jdbc:mysql:..";
    private static final String USER="root";
    private static final String PASSWORD="root";

    //Singleton Instance
    private static Connection connection;
    //Loading Database Driver

    private ConnectionManager(){
        //Avoir instantiate
    }

    public static Connection getConnection() throws RuntimeException {
        // Creating conecction to base
        if(connection == null){
            try {
                loadDriver();
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connection.setAutoCommit(false);
            }
            catch (SQLException e) {
               throw new RuntimeException(e);
            }
        }
        return connection;
    }
    public static void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Driver MySQL introuvable");
        }
    }
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
