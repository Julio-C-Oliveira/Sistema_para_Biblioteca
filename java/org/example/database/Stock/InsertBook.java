package org.example.database.Stock;

import org.example.database.DatabaseConnection;

import java.sql.SQLException;

public class InsertBook {
    public static void create(String title, String author, String published_at, Integer copies){

        String sql = "INSERT INTO books(title, author, published_at, copies) VALUES(?,?,?,?)";

        try (var conn = DatabaseConnection.connect();
             var pstmt = conn.prepareStatement(sql)) {
            System.out.println("Inserting " + title);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, published_at);
            pstmt.setInt(4, copies);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("BOOK CREATED SUCCESSFULLY");
    }
}
