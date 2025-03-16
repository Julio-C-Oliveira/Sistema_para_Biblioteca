package org.example.database.Role;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserRole {
    public static void updateRole(int user_id, int role) {
        String sql = "UPDATE user_role SET role = ? WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, role);
            pstmt.setInt(2, user_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("ROLE UPDATED SUCCESSFULLY");
    }
}
