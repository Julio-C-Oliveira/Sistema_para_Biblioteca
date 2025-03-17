package org.example.database.Stock;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class GetBook {
    public static Map<String, String> getBookByID(int id){
        String sql = "SELECT id, title, author, published_at, copies FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String published_at = rs.getString("published_at");
                Integer copies = rs.getInt("copies");
                return Map.of(
                        "id", Integer.toString(id),
                        "title", title,
                        "author", author,
                        "published_at", published_at,
                        "copies", copies.toString()
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public static Map<String, String> getBookByTitle(String title){
        String sql = "SELECT id, title, author, published_at, copies FROM books WHERE title = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                Integer id = rs.getInt("id");
                String author = rs.getString("author");
                String published_at = rs.getString("published_at");
                Integer copies = rs.getInt("copies");
                return Map.of(
                        "id", Integer.toString(id),
                        "title", title,
                        "author", author,
                        "published_at", published_at,
                        "copies", copies.toString()
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public static Map<String, String> getBookByAuthor(String author){
        String sql = "SELECT id, title, author, published_at, copies FROM books WHERE author = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, author);
            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String published_at = rs.getString("published_at");
                Integer copies = rs.getInt("copies");
                return Map.of(
                        "id", Integer.toString(id),
                        "title", title,
                        "author", author,
                        "published_at", published_at,
                        "copies", copies.toString()
                );
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public static ArrayList<Map<String, String>> getAllBooks() {
        String sql = "SELECT * FROM books";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Map<String, String>> books = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String published_at = rs.getString("published_at");
                Integer copies = rs.getInt("copies");

                Map<String, String> currentBook = Map.of(
                        "id", Integer.toString(id),
                        "title", title,
                        "author", author,
                        "published_at", published_at,
                        "copies", copies.toString()
                );
                books.add(currentBook);
            }

            return books;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

}
