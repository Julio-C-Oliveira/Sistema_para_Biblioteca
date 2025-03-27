package org.example.database.Stock;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBookTable {
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                " id INTEGER PRIMARY KEY," +
                " title TEXT UNIQUE," +
                " author TEXT," +
                "published_at TEXT," +
                "copies INTEGER" +
                ");";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("TABLE (BOOKS): TABLE CREATED SUCCESSFULLY");
    }
}
