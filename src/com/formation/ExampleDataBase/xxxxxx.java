package com.formation.ExampleDataBase;

import java.sql.*;

public class xxxxxx {
    private static final String URL="jdbc:mysql://localhost:8889/Example";
    private static final String USER="root";
    private static final String PASSWORD="root";
    public static void xxxxxx(String[] args) throws SQLException {

        //Loading Database Driver
        xxxxxx m = new xxxxxx();
        m.loadDriver();
        // Creating conecction to base
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        //QUERY SELECT
        String query = "SELECT * FROM fruits";
        try (Statement st = connection.createStatement()) {
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void loadDriver(){
        try { Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) { e.printStackTrace();
            System.err.println("Driver MySQL introuvable"); }
    }
}
