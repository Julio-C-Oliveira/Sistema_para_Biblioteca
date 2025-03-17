package org.example.database.Stock;

import org.example.database.DatabaseConnection;

import java.sql.SQLException;

public class DeleteBook {
    public static void delete(int id){
        var sql = "DELETE FROM books WHERE id = ?";

        try (var conn = DatabaseConnection.connect();
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("BOOK DELETED SUCCESSFULLY");
    }
}
