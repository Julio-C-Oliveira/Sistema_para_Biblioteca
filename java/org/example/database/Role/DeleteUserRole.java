package org.example.database.Role;

import org.example.database.DatabaseConnection;

import java.sql.SQLException;

public class DeleteUserRole {
    public static void delete(int id, int role) {
        var sql = "DELETE FROM user_role WHERE id = ? AND role = ?";

        try (var conn = DatabaseConnection.connect();
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setInt(1, role);

            pstmt.executeUpdate();
            return;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("ROLE DELETED SUCCESSFULLY");
    }
}
