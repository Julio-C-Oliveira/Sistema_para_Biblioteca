package org.example.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static String url = "jdbc:sqlite:db.db";
    
    public static Connection connect() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
            conn.createStatement().execute("PRAGMA busy_timeout = 3000;");
            System.out.println("Database connected.");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return conn;
    }
}