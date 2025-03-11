package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static String url = "jdbc:sqlite:/database/db.db";
    
    public static Connection connect() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Database connected.");
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return conn;
    }
}