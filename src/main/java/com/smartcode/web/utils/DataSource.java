package com.smartcode.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private Connection connection;
    private static DataSource instance;
    private final String url = "jdbc:postgresql://localhost:5432/MyDataBase";
    private final String username = "postgres";
    private final String password = "postgres";
    private final String driver = "org.postgresql.Driver";

    private DataSource() {
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected Successfully");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() {
        try {
            if (instance == null) {
                instance = new DataSource();
            } else if (instance.getConnection().isClosed()) {
                instance = new DataSource();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance;
    }
}